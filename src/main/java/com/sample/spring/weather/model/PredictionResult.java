package com.sample.spring.weather.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionResult {
    private List<DateToTemp> tavg ;
    private List<DateToTemp> tmin ;
    private List<DateToTemp> tmax ;
    public PredictionResult(){

    }
    public PredictionResult(List<DateToTemp> tavgIn, List<DateToTemp> tmaxIn, List<DateToTemp> tminIn){
        this.tavg = tavgIn;
        this.tmax = tmaxIn;
        this.tmin = tminIn;
    }

    public List<DateToTemp> getTavg() {
        return tavg;
    }

    public void setTavg(List<DateToTemp> tavgIn) {
        this.tavg = tavgIn;
    }

    public List<DateToTemp> getTmax() {
        return tmax;
    }

    public void setTmax(List<DateToTemp> tmaxIn) {
        this.tmax = tmaxIn;
    }

    public List<DateToTemp> getTmin() {
        return tmin;
    }

    public void setTmin(List<DateToTemp> tminIn) {
        this.tmin = tminIn;
    }

    @Override
    public String toString(){
        return "PredictionResult{"+
                "tavg="+tavg+""+
                ", tmax = "+ tmax +
                ", tmin = "+ tmin +
                "}";
    }
}
