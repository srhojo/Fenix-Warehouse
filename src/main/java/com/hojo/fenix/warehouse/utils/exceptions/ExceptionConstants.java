package com.hojo.fenix.warehouse.utils.exceptions;

/**
 * @author hojo
 */
public class ExceptionConstants {

    /**
     * Generic Exceptions
     */
    public static final String ERRORS_GENERIC_CODE = "001";
    public static final String ERRORS_GENERIC_MESSAGE = "An error has occurred [message=%s].";
    /**
     * DAO Exceptions
     */
    public static final String ERRORS_DAO_NOT_UPDATE_CODE = "010";
    public static final String ERRORS_DAO_NOT_UPDATE_MESSAGE = "Cannot update in a create method.";
    public static final String ERRORS_DAO_ENTITY_NOT_FOUND_CODE = "011";
    public static final String ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE = "Entity not found: [Id: %s ].";

    /**
     * Services Exceptions
     */

    private ExceptionConstants() {
        // Nothing here
    }


}
