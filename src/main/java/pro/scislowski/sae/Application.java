package pro.scislowski.sae;

import java.util.*;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class Application {

    public void start() {
        String inputFilePath = System.getProperty("input", "input.csv");
        String outputFilePath = System.getProperty("output", "output.csv");
        String delimiter = System.getProperty("delimiter", ";");

        CSVReader reader = new CSVReader(delimiter);
        List<Map<String, String>> records = reader.read(inputFilePath);
        System.out.println("######################");
        System.out.println(records.size() + " record(s) found");
        System.out.println("######################");

        CSVAppender appender = new CSVAppender(outputFilePath, delimiter);
        RestClient restClient = new RestClient();
        for (Map<String, String> record : records) {
            String result = restClient.send(record.get(Constants.CONTENTS_HEADER));
            appender.append(record, result);
            System.out.println("Result number " + record.get(Constants.NUMBER_HEADER) + " " + result);
        }
        appender.close();

        System.out.println("######################");
        System.out.println("Done");
        System.out.println("######################");
    }

}
