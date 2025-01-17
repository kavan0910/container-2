package com.kavankumar.ms2.controller;

import com.kavankumar.ms2.request.Request;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Ms2Controller {
    @PostMapping("/sum")
    public Map<String, Object> sum(@RequestBody Request request) {

        try {
            System.out.println("entered second api");
            final String STORAGE_LOCATION = "./kavan/files/";
            BufferedReader csv_file = new BufferedReader(new FileReader(STORAGE_LOCATION + request.getFile()));
            String row;
            int sum = 0;
            while ((row = csv_file.readLine()) != null) {
                System.out.println("In the file");
                String[] data = row.split(",");
                if (data[0].equals(request.getProduct())) {
                    sum += Integer.parseInt(data[1].trim());
                }
            }
            if (sum == 0) {
                System.out.println("product not found");
                HashMap<String, Object> map = new HashMap<>();
                map.put("file", request.getFile());
                map.put("error", "Input file not in CSV format.");
                return map;
            }
            HashMap<String, Object > map = new HashMap<>();
            System.out.println("Got product");
            map.put("file", request.getFile());
            map.put("sum", sum);
            return map;
        } catch (Exception e) {
            System.out.println(e);
            Map<String, Object> map = new HashMap<>();
            map.put("file", request.getFile());
            map.put("error", "Error occured");
            return map;
        }
    }
}
