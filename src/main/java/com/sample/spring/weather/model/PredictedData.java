package com.sample.spring.weather.model;

import java.util.List;

public class PredictedData {
    private String city;
    private List<PredictionResult> data;

    public PredictedData(){

    }

    public PredictedData(
            String cityIn,
            List<PredictionResult> dataIn) {
        this.city = cityIn;
        this.data = dataIn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String cityIn) {
        this.city = cityIn;
    }

    public List<PredictionResult> getData() {
        return data;
    }

    public void setData(List<PredictionResult> dataIn) {
        this.data = dataIn;
    }


    @Override
    public String toString(){
        return "PredictedData{"+
                "city="+city+""+
                ", data = "+ data +
                "}";
    }
}
