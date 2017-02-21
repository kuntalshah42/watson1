package pro.scislowski.sae;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class CSVReader {

    private String delimiter;

    public CSVReader(String delimiter) {
        this.delimiter = delimiter;
    }

    public List<Map<String, String>> read(String path) {
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();

        Reader in = null;
        try {
            in = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.DEFAULT.withDelimiter(delimiter.charAt(0))
                    .withFirstRecordAsHeader().withHeader(Constants.INPUT_HEADERS).parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord record : records) {
            Map<String, String> map = new HashMap<String, String>();
            for (String header : Constants.INPUT_HEADERS) {
                map.put(header, record.get(header));
            }
            results.add(map);
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

}
