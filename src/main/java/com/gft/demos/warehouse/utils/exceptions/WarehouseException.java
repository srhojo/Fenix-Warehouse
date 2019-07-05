package com.gft.demos.warehouse.utils.exceptions;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hojo
 *
 */
@ApiModel(value = "WarehouseException", description = "Custom Runtime exception")
public class WarehouseException extends RuntimeException {

    private static final long serialVersionUID = 3287289611448622533L;

    @ApiModelProperty(value = "HttpStatus.", example = "401, 500,...")
    private final HttpStatus status;

    @ApiModelProperty(value = "Unique identification code", example = "001,010,...")
    private final String code;

    @ApiModelProperty(value = "Generic java object who contains extra exception information.")
    private final transient Object details;

    public WarehouseException(final Object details) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = ExceptionConstants.ERRORS_GENERIC_CODE;
        this.details = details;
    }

    public WarehouseException(final HttpStatus status, final String code, final Object details) {
        this.status = status;
        this.code = code;
        this.details = details;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
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
