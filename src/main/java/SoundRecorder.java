
import com.dropbox.core.v2.DbxClientV2;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/* Simple program is for demonstrate how to record sound in Java*/
public class SoundRecorder {

    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    TargetDataLine line;
    DbxClientV2 dbxClientV2;
    AudioFormat format;


    public SoundRecorder(DbxClientV2 clientV2) {

        this.dbxClientV2 = clientV2;

        format = getAudioFormat();

        DataLine.Info info = new DataLine.Info(TargetDataLine.class , format);

        // checks if system supports the data line
        if (!AudioSystem.isLineSupported(info)) {

            System.out.println("Line is not supported");
            System.exit(0);
        }

        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    AudioFormat getAudioFormat() {

        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate ,
                sampleSizeInBits ,
                channels ,
                signed ,
                bigEndian);
    }


    public void recordAudio(int milliseconds) {

        // 20210102_201454.wav - name example

        String timeStamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime());

        String fileName = "C://Users/Yurko/Desktop/Programming/" + timeStamp + ".wav";

        File file = new File(fileName);

        start(file);
        stop(milliseconds , file);
    }


    // Captures the sound and record into a WAV file
    void start(File file) {

        Thread thread = new Thread() {
            @Override
            public void run() {

                try {

                    line.open(format);
                    line.start();  // start capturing

                    AudioInputStream inputStream = new AudioInputStream(line);

                    AudioSystem.write(inputStream , fileType , file); // start recording

                } catch (LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
            }

        };

        thread.start();
    }


    // Closes the target data line to finish capturing and recording
    void stop(int milliseconds , File file) {

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(milliseconds);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

                line.stop();
                line.close();

                // Upload files to Dropbox
                DropboxUploader dropboxUploader = new DropboxUploader();
                dropboxUploader.uploadRecord(file.getPath() , file.getName());

                // Delete file
                try {
                    Thread.sleep(500);
                    System.gc();
                    Files.delete(Paths.get(String.valueOf(file)));

                } catch (InterruptedException | IOException exception) {
                    exception.printStackTrace();
                }
            }

        };

        thread.start();
    }

}
