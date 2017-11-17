package com.sample.spring.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Consumer {
    private Integer day;
    private Integer temperature;
    public Consumer(){

    }
    public Consumer(Integer id, Integer temperature){
        this.day = id;
        this.temperature = temperature;
    }
    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getDay() {

        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
    @Override
    public String toString(){
     return "Consume{"+
             "id="+day+""+
             ", temperature = "+ temperature +
             "}";
    }
}
