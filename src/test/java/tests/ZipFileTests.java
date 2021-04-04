package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.deleteFile;
import static utils.Files.readTextFromFile;
import static utils.Zip.unzip;

public class ZipFileTests {
    private static final String UNZIP_FOLDER_PATH = "unzip";

    @Test
    void zipWithPasswordTest() {
        String zipFile = "1.zip";
        String zipPassword = "pass123";
        String unzipDocFilePath = "unzip/1.txt";

        unzip(zipFile, UNZIP_FOLDER_PATH, zipPassword);

        String actualData = readTextFromFile(unzipDocFilePath);
        String expectedData = "How can I download some file in my test?";
        assertThat(actualData, containsString(expectedData));
    }

    @AfterAll
    static void afterAll() {
        deleteFile(UNZIP_FOLDER_PATH);
    }
}
