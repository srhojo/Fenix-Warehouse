package com.hojo.fenix.warehouse.utils.ql.specfications;

import java.util.Optional;
import java.util.stream.Stream;

public enum SearchOperationEnum {
    EQUALITY("=="),
    NEGATION("!="),
    GREATER_THAN("=gt="),
    LESS_THAN("=lt="),
    LIKE("=in=");

    private final String symbol;

    SearchOperationEnum(final String symbol) {
        this.symbol = symbol;
    }

    public static String[] operationSet() {
        return Stream.of(SearchOperationEnum.values()).map(SearchOperationEnum::getSymbol).toArray(String[]::new);
    }

    public static Optional<SearchOperationEnum> getSimpleOperation(final String input) {
        SearchOperationEnum operation;
        switch (input) {
            case "==":
                operation = EQUALITY;
                break;
            case "!=":
                operation = NEGATION;
                break;
            case "=gt=":
                operation = GREATER_THAN;
                break;
            case "=lt=":
                operation = LESS_THAN;
                break;
            case "=in=":
                operation = LIKE;
                break;
            default:
                operation = null;
                break;
        }
        return Optional.ofNullable(operation);

    }

    /**
     * @return the symbol of operation
     */
    private String getSymbol() {
        return symbol;
    }
}
