package com.gft.demos.warehouse.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.gft.demos.warehouse.utils.exceptions.ExceptionConstants.*;

/**
 * @author hojo
 */
@RestControllerAdvice
public class WarehouseRestControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Method to response handled exceptions
     *
     * @param warehouseException the handled warehouse exception
     * @return ResponseEntity<WarehouseExceptionResponse>
     */
    @ExceptionHandler(WarehouseException.class)
    public ResponseEntity<WarehouseExceptionResponse> handleWarehouseException(
            final WarehouseException warehouseException) {
        final WarehouseExceptionResponse response = WarehouseExceptionResponse.of(warehouseException.getStatus(),
                warehouseException.getCode(), warehouseException.getDetails());
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * Method to response unhandled exceptions
     *
     * @param runtimeException the generic runtime exception
     * @return ResponseEntity<WarehouseExceptionResponse>
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<WarehouseExceptionResponse> handleRuntimeException(final RuntimeException runtimeException) {
        final WarehouseExceptionResponse wer = WarehouseExceptionResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,
                ERRORS_GENERIC_CODE, String.format(ERRORS_GENERIC_MESSAGE, runtimeException.getMessage()));
        return new ResponseEntity<>(wer, wer.getStatus());
    }

    /**
     * Method to handle constraint exceptions
     *
     * @param constraintViolationException the constraint exception
     * @return ResponseEntity<WarehouseExceptionResponse>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WarehouseExceptionResponse> handleRuntimeException(final ConstraintViolationException constraintViolationException) {
        final WarehouseExceptionResponse wer = WarehouseExceptionResponse.of(HttpStatus.BAD_REQUEST,
                ERRORS_VALIDATION_CODE, String.format(ERRORS_VALIDATION_MESSAGE, constraintViolationException.getMessage()));
        return new ResponseEntity<>(wer, wer.getStatus());
    }

}
