package com.sample.spring.weather.controller;

import com.sample.spring.weather.DataRepository;
import com.sample.spring.weather.model.DataSource;
import com.sample.spring.weather.WeatherAppException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class DataController {

    private static final String [] HEADER_MAPPING= {"DATE", "TAVG", "STATION", "NAME", "ELEVATION", "TMAX", "TMIN", "CITY"};

    private static final String DATE = "DATE";
    private static final String TAVG = "TAVG";
    private static final String STATION = "STATION";
    private static final String NAME = "NAME";
    private static final String ELEVATION = "ELEVATION";
    private static final String TMAX = "TMAX";
    private static final String TMIN = "TMIN";
    private static final String CITY = "CITY";

    @Autowired
    private DataRepository repository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value ="/read")
    public String fileRead() throws IOException {
        try {
            Resource resource = resourceLoader.getResource("classpath:data.csv");
            InputStream fileAsStream = resource.getInputStream(); // <-- this is the difference
            BufferedReader br = new BufferedReader(new InputStreamReader(fileAsStream));

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADER_MAPPING)
                    .withFirstRecordAsHeader()
                    .parse(br);

            for (CSVRecord record: records){
                DataSource row = new DataSource(record.get(DATE),
                        record.get(NAME),
                        Integer.parseInt(record.get(TAVG)),
                        Integer.parseInt(record.get(TMIN)),
                        Integer.parseInt(record.get(TMAX)),
                        Float.parseFloat(record.get(ELEVATION)),
                        record.get(STATION),
                        record.get(CITY)
                        );
                DataSource response = repository.save(row);
            }

            return "data source saved success";
        } catch (FileNotFoundException e) {
            throw new WeatherAppException(String.format("File not found exception.Reason : %s",e.getMessage()),e);
        } catch (Exception e) {
            throw new WeatherAppException(String.format("Weather datasource failed exception. Reason : %s",e.getMessage()),e);
        }
    }
}
