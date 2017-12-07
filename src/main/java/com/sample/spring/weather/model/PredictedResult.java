package com.sample.spring.weather.model;

public class PredictedResult {
    private String date;
    private int tmax_f;
    private int tmin_f;
    private int tavg_f;
    private int tmax_c;
    private int tmin_c;
    private int tavg_c;
    private String city;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTmax_F() {
        return tmax_f;
    }

    public void setTmax_F(int tmax_f) {
        this.tmax_f = tmax_f;
    }

    public int getTmin_F() {
        return tmin_f;
    }

    public void setTmin_F(int tmin_f) {
        this.tmin_f = tmin_f;
    }

    public int getTavg_F() {
        return tavg_f;
    }

    public void setTavg_F(int tavg_f) {
        this.tavg_f = tavg_f;
    }

    public int getTmax_C() {
        return tmax_c;
    }

    public void setTmax_C(int tmax_c) {
        this.tmax_c = tmax_c;
    }

    public int getTmin_C() {
        return tmin_c;
    }

    public void setTmin_C(int tmin_c) {
        this.tmin_c = tmin_c;
    }

    public int getTavg_C() {
        return tavg_c;
    }

    public void setTavg_C(int tavg_c) {
        this.tavg_c = tavg_c;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PredictedResult(
        String date,
        int tmax_f,
        int tmin_f,
        int tavg_f,
        int tmax_c,
        int tmin_c,
        int tavg_c,
        String city) {
        this.date = date;
        this.tmax_f = tmax_f;
        this.tmin_f = tmin_f;
        this.tavg_f = tavg_f;
        this.tmax_c = tmax_f;
        this.tmin_c = tmin_f;
        this.tavg_c = tavg_f;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format(
                "date = %s name=%s avg= %d max= %d min=%d elevation=%f station=%s",
                date,
                tmax_f,
                tmin_f,
                tavg_f,
                tmax_c,
                tmin_c,
                tavg_c,
                city);
    }
}
