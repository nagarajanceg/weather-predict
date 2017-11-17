package com.sample.spring.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GetDataCollection {
    @Autowired
    private DataRepository repository;
    @GetMapping(value = "/getdata")
    public List<DataSource> RetriveData(){
        List<DataSource> dataList = repository.findAll();
//        for(DataSource element: dataList){
//            System.out.println(element);
//        }
        return dataList;
    }
}
