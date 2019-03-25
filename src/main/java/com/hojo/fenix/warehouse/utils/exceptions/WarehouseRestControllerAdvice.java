package com.hojo.fenix.warehouse.utils.exceptions;

import static com.hojo.fenix.warehouse.utils.exceptions.ExceptionConstants.ERRORS_GENERIC_CODE;
import static com.hojo.fenix.warehouse.utils.exceptions.ExceptionConstants.ERRORS_GENERIC_MESSAGE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author hojo
 *
 */
@RestControllerAdvice
public class WarehouseRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WarehouseException.class)
    public ResponseEntity<WarehouseExceptionResponse> handleWarehouseException(
            final WarehouseException warehouseException) {
        final WarehouseExceptionResponse response = WarehouseExceptionResponse.of(warehouseException.getStatus(),
                warehouseException.getCode(), warehouseException.getDetails());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<WarehouseExceptionResponse> handleRuntimeException(final RuntimeException runtimeException) {
        final WarehouseExceptionResponse wer = WarehouseExceptionResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,
                ERRORS_GENERIC_CODE, String.format(ERRORS_GENERIC_MESSAGE, runtimeException.getMessage()));
        return new ResponseEntity<>(wer, wer.getStatus());
    }

}
