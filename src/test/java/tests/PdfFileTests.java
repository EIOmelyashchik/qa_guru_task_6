package tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.Files.getPdf;

public class PdfFileTests {

    @Test
    void checkPdfFile() {
        String namePdfFile = "1.pdf";
        String expectedData = "How can I download some file in my test?";

        PDF pdf = getPdf(namePdfFile);
        assertThat(pdf, PDF.containsText(expectedData));
    }
}
