package com.sample.spring.weather;

import java.util.List;

import com.sample.spring.weather.model.DataSource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<DataSource, String>{
    public List<DataSource> findByCity(String city);
}
