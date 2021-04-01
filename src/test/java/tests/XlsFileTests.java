package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Files;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XlsFileTests {
    List<List<Object>> expectedData = new ArrayList<List<Object>>() {{
        add(Arrays.asList("Andrei", "Andreev", LocalDateTime.of(1999, Month.DECEMBER, 19, 0, 0, 0), "60000"));
        add(Arrays.asList("Ivan", "Ivanov", LocalDateTime.of(1999, Month.DECEMBER, 20, 0, 0, 0), "55000"));
        add(Arrays.asList("Artem", "Artemov", LocalDateTime.of(1999, Month.DECEMBER, 21, 0, 0, 0), "70000"));
        add(Arrays.asList("Pavel", "Pashkov", LocalDateTime.of(1999, Month.DECEMBER, 22, 0, 0, 0), "120000"));
        add(Arrays.asList("Alena", "Lenina", LocalDateTime.of(1999, Month.DECEMBER, 23, 0, 0, 0), "90000"));
        add(Arrays.asList("Daria", "Dashkova", LocalDateTime.of(1999, Month.DECEMBER, 24, 0, 0, 0), "150000"));
    }};

    @Test
    void checkXlsFile() {
        String nameXlsFile = "1.xls";

        List<List<Object>> actualData = Files.readDataFromXlsFile(nameXlsFile);
        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void checkXlsxFile() {
        String nameXlsxFile = "1.xlsx";

        List<List<Object>> actualData = Files.readDataFromXlsFile(nameXlsxFile);
        Assertions.assertEquals(expectedData, actualData);
    }
}
