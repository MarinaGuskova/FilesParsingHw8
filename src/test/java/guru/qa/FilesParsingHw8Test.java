package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingHw8Test {
    ClassLoader cl = FilesParsingHw8Test.class.getClassLoader();

    @Test
    void pdfZipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("example.zip");
                ZipInputStream zis = new ZipInputStream(resource)

        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                if (entryName.contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(entry.getName()).isEqualTo("sample.pdf");
                    assertThat(content.encrypted).isFalse();
                    assertThat(content.text).contains("More text");
                } else if (entryName.contains(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(3).getCell(0).getNumericCellValue()).isIn(47008.0);
                    assertThat(content.excel.getSheetAt(1).getRow(2).getCell(1).getStringCellValue()).contains("регион2");
                } else if (entryName.contains("example.csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(1)[1]).contains("Барсик");
                }
            }
        }
    }
}
