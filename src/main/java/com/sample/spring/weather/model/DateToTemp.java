package com.sample.spring.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateToTemp {
    private String date ;
    private Integer temp ;
    public DateToTemp(){

    }
    public DateToTemp(String dateIn, Integer tempIn){
        this.date = dateIn;
        this.temp = tempIn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateIn) {
        this.date = dateIn;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer tempIn) {
        this.temp = tempIn;
    }

    @Override
    public String toString(){
        return "DateToTemp{"+
                "date="+date+""+
                ", temp = "+ temp +
                "}";
    }
}
