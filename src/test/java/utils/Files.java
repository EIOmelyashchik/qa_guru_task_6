package utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Files {
    private static final String PATH_TO_FILES = "./src/test/resources/files/";

    public static String readTextFromFile(File file) {
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read file " + file.getPath(), e);
        }
    }

    public static String readTextFromFile(String filePath) {
        return readTextFromFile(getFile(filePath));
    }

    public static File getFile(String filePath) {
        return new File(PATH_TO_FILES + filePath);
    }

    public static PDF getPdf(String filePath) {
        File file = getFile(filePath);
        try {
            return new PDF(file);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read pdf-file " + file.getPath(), e);
        }
    }

    public static XLS getXls(String filePath) {
        return new XLS(getFile(filePath));
    }

    public static List<List<Object>> readDataFromXlsFile(String filePath) {
        List<List<Object>> xlsData = new ArrayList<>();
        XLS xls = getXls(filePath);
        Sheet sheet = xls.excel.getSheetAt(0);
        for (Row row : sheet) {
            List<Object> values = new ArrayList<>();
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        values.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell))
                            values.add(cell.getLocalDateTimeCellValue());
                        else
                            values.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                        break;
                    case BOOLEAN:
                        values.add(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        FormulaEvaluator evaluator = xls.excel.getCreationHelper().createFormulaEvaluator();
                        values.add(evaluator.evaluate(cell).getNumberValue());
                        break;
                    default:
                        values.add("");
                }
            }
            xlsData.add(values);
        }
        try {
            xls.excel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xlsData;
    }

    public static void deleteFile(String filePath) {
        try {
            FileUtils.deleteDirectory(getFile(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
