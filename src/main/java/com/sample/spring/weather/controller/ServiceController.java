package com.sample.spring.weather.controller;

import com.sample.spring.weather.model.PredictedData;
import com.sample.spring.weather.model.PredictionResult;
import com.sample.spring.weather.Hello;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.core.ParameterizedTypeReference;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServiceController {
    @Value("${python.weather.app.url}")
    private String getUrl;
    RestTemplate restTemplate = new RestTemplate();
    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/consume")
    public ArrayList<PredictedData> getService(){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<PredictionResult> httpEntity = new HttpEntity<>(httpHeaders);

        ArrayList<PredictedData> res = new ArrayList<PredictedData>();
        PredictedData predictedData = null;

        ResponseEntity<List<PredictionResult>> quote =
                restTemplate.exchange(getUrl+"/ModelGeneration/NEW_YORK", HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<List<PredictionResult>>() {});

        if(quote.getBody() != null){
            System.out.println(quote.getBody().toString());
            predictedData = new PredictedData("NEW_YORK", quote.getBody());
            res.add(0, predictedData);
            predictedData = null;
        }else{
            System.out.println("Problem in Consuming Rest service");
        }

        ResponseEntity<List<PredictionResult>> quote2 =
                restTemplate.exchange(getUrl+"/ModelGeneration/BINGHAMTON", HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<List<PredictionResult>>() {});

        if(quote2.getBody() != null){
            System.out.println(quote2.getBody().toString());
            predictedData = new PredictedData("BINGHAMTON", quote2.getBody());
            res.add(0, predictedData);
            predictedData = null;
        }else{
            System.out.println("Problem in Consuming Rest service");
        }

       ResponseEntity<List<PredictionResult>> quote3 =
                restTemplate.exchange(getUrl+"/ModelGeneration/SAN_FRANCISCO", HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<List<PredictionResult>>() {});

        if(quote3.getBody() != null){
            System.out.println(quote3.getBody().toString());
            predictedData = new PredictedData("SAN_FRANCISCO", quote3.getBody());
            res.add(0, predictedData);
            predictedData = null;
        }else{
            System.out.println("Problem in Consuming Rest service");
        }

        return res;
    }

    @GetMapping(value = "/publish")
    public PredictionResult putService(){
        return null;
    }

    @GetMapping(value = "/sayHello")
    public Hello sayHello(){
        return  new Hello("Hello from Spring Boot");
    }

    @GetMapping(value= "/consumeHello")
    public Hello consumeHello(){
        ResponseEntity<Hello> hello = restTemplate.getForEntity(getUrl+"/sayHello",Hello.class);
        return hello.getBody();
    }
}
