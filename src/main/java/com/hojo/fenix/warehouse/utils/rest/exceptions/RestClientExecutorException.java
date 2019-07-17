package com.hojo.fenix.warehouse.utils.rest.exceptions;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

public class RestClientExecutorException extends RuntimeException {

    private static final long serialVersionUID = -548900993881141526L;

    @ApiModelProperty(value = "HttpStatus.", example = "401, 500,...")
    private final HttpStatus status;

    @ApiModelProperty(value = "Unique identification code", example = "001,010,...")
    private final String code;

    @ApiModelProperty(value = "Generic java object who contains extra exception information.")
    private final transient Object details;

    public RestClientExecutorException(final HttpStatus status, final String code, final Object details) {
        this.status = status;
        this.code = code;
        this.details = details;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public Object getDetails() {
        return details;
    }
}