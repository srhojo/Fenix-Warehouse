package com.gft.demos.warehouse.utils.exceptions;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

/**
 * @author hojo
 */
public class WarehouseExceptionResponse {

    @ApiModelProperty(value = "HttpStatus.", example = "401, 500,...")
    private final HttpStatus status;

    @ApiModelProperty(value = "Unique identification code", example = "001,010,...")
    private final String code;

    @ApiModelProperty(value = "Generic java object who contains extra exception information.")
    private final Object details;

    private WarehouseExceptionResponse(final HttpStatus status, final String code, final Object details) {
        this.status = status;
        this.code = code;
        this.details = details;
    }

    public static WarehouseExceptionResponse of(final HttpStatus status, final String code, final Object details) {
        return new WarehouseExceptionResponse(status, code, details);
    }

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the details
     */
    public Object getDetails() {
        return details;
    }

}
