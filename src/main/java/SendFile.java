import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class SendFile {
    private final static String ACCESS_TOKEN = "sl.Ao-hotLVZrGW0cYvM8MWRPtd3LfwPWc8Rmam6YzTT7E9fClPZOFfDdt89-eNog0aVP6jAxHTRKWXgdOGLt3OF57NX8ZKYK4CSdSww4_6abMqmSFWjVnrpdDRWR-VpTFmLnjzKBQ";

    public static void main(String[] args) {

        uploadFile();

    }

    // Upload "test.txt" to Dropbox
    static void uploadFile() {

        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config , ACCESS_TOKEN);

        String file = "C://Users/Yurko/Desktop/Programming/java-coding.jpg";

        try {

            InputStream inputStream = new FileInputStream(file);
            client.files().uploadBuilder("/test.jpg").uploadAndFinish(inputStream);

        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }
    }

}
