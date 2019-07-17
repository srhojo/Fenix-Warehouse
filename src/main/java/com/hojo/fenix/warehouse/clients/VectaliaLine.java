package com.hojo.fenix.warehouse.clients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties
public class VectaliaLine implements Serializable {

    private static final long serialVersionUID = -1766744644143245879L;

    private String line;
    private List<VectaliaStop> stops;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<VectaliaStop> getStops() {
        return stops;
    }

    public void setStops(List<VectaliaStop> stops) {
        this.stops = stops;
    }
}
