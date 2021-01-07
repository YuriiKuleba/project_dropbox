import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    private final static String ACCESS_TOKEN = "sl.Ao7NcxJM5dV__BKD9znb_HuLIRMJDdLUYn3-uPdQ2lwX2mGaFfsC3fZorcHWh-" +
            "p9RjWmj3GtI5UR6zfI3e6csqVHFY5ge9JcBE1fahPnTUgJ03APk1_bvYrtH0JRpAztxXb0mvI";


    public static void main(String[] args) {


        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config , ACCESS_TOKEN);

        String file = "C://Users/Yurko/Desktop/Programming/java.jpg";

        // Upload "test.txt" to Dropbox
        try {
            InputStream inputStream = new FileInputStream(file);
            client.files().uploadBuilder("/test.jpg").uploadAndFinish(inputStream);
            } catch (IOException | DbxException e) {
            e.printStackTrace();
        }


    }
    }
