import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUploader {

    private final static String ACCESS_TOKEN = "sl.Ao-hotLVZrGW0cYvM8MWRPtd3LfwPWc8Rmam6YzTT7E9fClPZOFfDdt89-eNog0aVP6jAxHTRKWXgdOGLt3OF57NX8ZKYK4CSdSww4_6abMqmSFWjVnrpdDRWR-VpTFmLnjzKBQ";

    // Create Dropbox client
    private DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    private DbxClientV2 client = new DbxClientV2(config , ACCESS_TOKEN);

    public void uploadRecord(String filePath , String fileName) {

        try {
            InputStream inputStream = new FileInputStream(filePath);

            FileMetadata metadata = client.files().uploadBuilder("/" + fileName).uploadAndFinish(inputStream);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public DbxClientV2 getClient() {
        return client;
    }
}
