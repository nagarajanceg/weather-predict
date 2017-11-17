package com.sample.spring.weather;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class DataController {
    @Value("${weather.data.path}")
    private String dataPath;
    private static final String [] HEADER_MAPPING= {"DATE", "TAVG", "STATION", "NAME", "ELEVATION", "TMAX", "TMIN"};

    private static final String DATE = "DATE";
    private static final String TAVG = "TAVG";
    private static final String STATION = "STATION";
    private static final String NAME = "NAME";
    private static final String ELEVATION = "ELEVATION";
    private static final String TMAX = "TMAX";
    private static final String TMIN = "TMIN";

    @Autowired
    private DataRepository repository;

    @GetMapping(value ="/read")
    public void fileRead() throws IOException {
        System.out.println(dataPath);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(dataPath);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADER_MAPPING)
                    .withFirstRecordAsHeader()
                    .parse(fileReader);
            for (CSVRecord record: records){
//new SimpleDateFormat("dd/MM/yy").parse(record.get(DATE))
                DataSource row = new DataSource(record.get(DATE),
                        record.get(NAME),
                        Integer.parseInt(record.get(TAVG)),
                        Integer.parseInt(record.get(TMIN)),
                        Integer.parseInt(record.get(TMAX)),
                        Float.parseFloat(record.get(ELEVATION)),
                        record.get(STATION)
                        );
                repository.save(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
