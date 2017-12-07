package com.sample.spring.weather.controller;

import com.sample.spring.weather.DataRepository;
import com.sample.spring.weather.model.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
public class GetDataSource {
    @Autowired
    private DataRepository repository;
    @GetMapping(value = "/getdata")
    public List<DataSource> RetriveData(){
        List<DataSource> dataList = repository.findAll();
        return dataList;
    }

    @GetMapping(value = "/getdata/{city}")
    public List<DataSource> RetriveDataByCity(HttpServletRequest req){
        List<DataSource> dataList =
                repository.findByCity(req.getParameter("city").toString());
        return dataList;
    }
}


