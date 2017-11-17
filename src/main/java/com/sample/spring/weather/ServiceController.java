package com.sample.spring.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@RestController
public class ServiceController {
    @Value("${python.weather.app.url}")
    private String getUrl;
    RestTemplate restTemplate = new RestTemplate();
    @GetMapping(value = "/consume")
    public List<Consumer> getService(){
//        System.out.println("test url == "+getUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Consumer> httpEntity = new HttpEntity<>(httpHeaders);
        List<Consumer> res = null;
        ResponseEntity<List<Consumer>> quote = restTemplate.exchange(getUrl+"/test", HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<List<Consumer>>() {});
        if(quote.getBody() != null){
            System.out.println(quote.getBody().toString());
            res = quote.getBody();
        }else{
            System.out.println("Problem in Consuming Rest service");
        }
        return res;
    }
    @GetMapping(value = "/publish")
    public Consumer putService(){
//        Consumer result = null;
//        System.out.println("put Service");
//        result = new Consumer(4, 54);
        return null;
    }
}
