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


    public static final String ERRORS_VALIDATION_CODE = "002";
    public static final String ERRORS_VALIDATION_MESSAGE = "A validation error has occurred [message=%s].";
    /**
     * DAO Exceptions
     */
    public static final String ERRORS_DAO_NOT_UPDATE_CODE = "010";
    public static final String ERRORS_DAO_NOT_UPDATE_MESSAGE = "Cannot update in a create method.";
    public static final String ERRORS_DAO_ENTITY_NOT_FOUND_CODE = "011";
    public static final String ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE = "Entity not found: [Id: %s ].";

    public static final String ERRORS_DAO_SAVE_FOOD_CODE = "020";
    public static final String ERRORS_DAO_SAVE_FOOD_MESSAGE = "An error has occurred saving food: { object: %s , error: %s }.";

    public static final String ERRORS_DAO_SAVE_SHOPPING_LIST_CODE = "030";
    public static final String ERRORS_DAO_SAVE_SHOPPING_LIST_MESSAGE = "An error has occurred saving shopping list: { object: %s , error: %s }.";


    /**
     * Services Exceptions
     */

    private ExceptionConstants() {
        // Nothing here
    }


}
