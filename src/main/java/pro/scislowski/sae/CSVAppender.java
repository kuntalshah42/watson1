package pro.scislowski.sae;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class CSVAppender {

    private CSVPrinter printer;
    private FileWriter fileWriter;

    public CSVAppender(String path, String delimiter) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(delimiter.charAt(0)).withHeader(Constants.OUTPUT_HEADERS);
        Path file = Paths.get(path);
        if (file.toFile().exists()) {
            file.toFile().delete();
        }
        try {
            Path filePath = Files.createFile(file);
            fileWriter = new FileWriter(filePath.toFile());
            printer = new CSVPrinter(fileWriter, csvFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void append(Map<String, String> record, String result) {
        List<String> values = new ArrayList<>(record.values());
        values.add(result);
        try {
            printer.printRecord(values);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            fileWriter.flush();
            fileWriter.close();
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
