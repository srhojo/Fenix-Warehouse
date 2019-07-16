package com.hojo.fenix.warehouse.utils.ql.expections;

public class QueryLanguageException extends RuntimeException {

    /**
     *
     * @param message
     */
    public QueryLanguageException(final String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public QueryLanguageException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
