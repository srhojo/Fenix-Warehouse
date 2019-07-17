package com.hojo.fenix.warehouse.clients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties
public class VectaliaStop implements Serializable {

    private static final long serialVersionUID = -4554052216777128649L;

    private Integer time;
    private String destinationName;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}
