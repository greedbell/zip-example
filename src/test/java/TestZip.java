import org.junit.Test;
import util.FileHelper;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestZip {

    @Test
    public void testArchive() throws IOException {
        File zipFile = FileHelper.autoCreateFile("build/tmp/compress.zip");
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (int index = 0; index < 100; index++) {
            URL url = this.getClass().getResource("/test.jpg");
            File fileToZip = new File(url.getPath());
            FileInputStream fis = new FileInputStream(fileToZip);

            String name = "test" + index + ".jpg";
            ZipEntry zipEntry = new ZipEntry(name);
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }

        zipOut.close();
        fos.close();
    }
}
