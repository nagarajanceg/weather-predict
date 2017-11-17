package com.sample.spring.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataSource {
    private String date;
//    private int temperature;
    private String name;
    private int tmax;
    private int tmin;
    private int tavg;
    private float elevation;
    private String station;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTmax() {
        return tmax;
    }

    public void setTmax(int tmax) {
        this.tmax = tmax;
    }

    public int getTmin() {
        return tmin;
    }

    public void setTmin(int tmin) {
        this.tmin = tmin;
    }

    public int getTavg() {
        return tavg;
    }

    public void setTavg(int tavg) {
        this.tavg = tavg;
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public DataSource(String date, String name, int tavg, int tmin, int tmax, float elevation, String station) {
        this.date = date;
        this.tavg = tavg;
        this.tmax = tmax;
        this.tmin = tmin;
        this.name = name;

        this.elevation = elevation;
        this.station = station;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("date = %s name=%s avg= %d max= %d min=%d elevation=%f station=%s",date, name, tavg, tmax,
                tmin, elevation, station  );
    }
}