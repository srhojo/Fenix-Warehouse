package com.hojo.fenix.warehouse.clients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hojo.fenix.warehouse.utils.rest.RestClientObject;

import java.util.List;

@JsonIgnoreProperties
public class VectaliaLines implements RestClientObject {

    private static final long serialVersionUID = 8929369726946355548L;

    private List<VectaliaLine> lines;

    public List<VectaliaLine> getLines() {
        return lines;
    }

    public void setLines(List<VectaliaLine> lines) {
        this.lines = lines;
    }
}
