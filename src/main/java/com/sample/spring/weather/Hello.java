package com.sample.spring.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hello {
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Hello() {

    }
    public Hello(String message) {
        this.message = message;
    }
    @Override
    public String toString(){
        return "Consume message = "+ message;
    }
}
