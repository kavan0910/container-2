package com.kavankumar.ms2.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

@RestController
public class Ms2Controller {
    @PostMapping("/sum")
    public Map<String, Object> sum(@RequestBody Map<String, String> request) {
        final String STORAGE_LOCATION = "./kavan/files/";
        String fileName = request.get("file");
        String product = request.get("product");
        File file = new File(STORAGE_LOCATION + fileName);
        if (!file.exists()) {
            return Map.of("file", fileName, "error", "File not found.");
        }

        try (FileReader reader = new FileReader(file)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            int sum = 0;
            for (CSVRecord record : records) {
                if (record.get("product").equalsIgnoreCase(product)) {
                    sum += Integer.parseInt(record.get("amount"));
                }
            }
            return Map.of("file", fileName, "sum", String.valueOf(sum));
        } catch (IOException e) {
            return Map.of("file", fileName, "error", "Input file not in CSV format.");
        }
    }
}
