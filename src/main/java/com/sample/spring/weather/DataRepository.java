package com.sample.spring.weather;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<DataSource, String>{

}
