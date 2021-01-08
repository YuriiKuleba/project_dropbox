import com.dropbox.core.v2.DbxClientV2;

class Main {

    public static void main(String[] args) {


        DbxClientV2 client = new DropboxUploader().getClient();
        SoundRecorder soundRecorder = new SoundRecorder(client);

        soundRecorder.recordAudio(10000);


    }
}
