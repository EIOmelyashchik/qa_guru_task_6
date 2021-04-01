package utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Zip {
    private static final String PATH_TO_FILES = "./src/test/resources/files/";

    public static void unzip(String path, String unzipPath, String password) {
        try {
            ZipFile zipFile = new ZipFile(PATH_TO_FILES + path);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(PATH_TO_FILES + unzipPath);
        } catch (ZipException e) {
            throw new IllegalArgumentException("Failed to extract zip-file " + PATH_TO_FILES + path, e);
        }
    }
}
