import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;
import util.FileHelper;

import java.io.*;
import java.net.URL;

public class TestApacheZip {
    @Test
    public void testArchive() throws IOException {
        File zipFile = FileHelper.autoCreateFile("build/tmp/compress.zip");
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(zipFile);

        for (int index = 0; index < 100; index++) {
            String name = "test" + index + ".jpg";
            ZipArchiveEntry entry = new ZipArchiveEntry(name);
            zos.putArchiveEntry(entry);

            URL url = this.getClass().getResource("/test.jpg");
            File fileToZip = new File(url.getPath());
            FileInputStream fis = new FileInputStream(fileToZip);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
            fis.close();
        }
        zos.closeArchiveEntry();
        zos.finish();
    }

    @Test
    public void testArchive1() throws IOException {
        File zipFile = FileHelper.autoCreateFile("build/tmp/compress.zip");
        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(zipFile);) {
            for (int index = 0; index < 100; index++) {
                String name = "test" + index + ".jpg";
                ZipArchiveEntry entry = new ZipArchiveEntry(name);
                zos.putArchiveEntry(entry);

                URL url = this.getClass().getResource("/test.jpg");
                File fileToZip = new File(url.getPath());

                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    IOUtils.copy(fis, zos);
                }
            }
            zos.closeArchiveEntry();
            zos.finish();
        }
    }
}
