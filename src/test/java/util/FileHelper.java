package util;

import org.junit.Assert;

import java.io.File;
import java.io.IOException;

public class FileHelper {
    public static File autoCreateFile(String path) throws IOException {
        File zipFile = new File(path);

        File zipFileDirectory = zipFile.getParentFile();
        if (!zipFileDirectory.exists()) {
            Assert.assertTrue(zipFileDirectory.mkdirs());
        }

        if (zipFile.exists()) {
            Assert.assertTrue(zipFile.delete());
        }
        Assert.assertTrue(zipFile.createNewFile());

        return zipFile;
    }
}
