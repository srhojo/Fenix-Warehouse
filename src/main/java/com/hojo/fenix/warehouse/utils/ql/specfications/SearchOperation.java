package com.hojo.fenix.warehouse.utils.ql.specfications;

import java.util.Optional;
import java.util.stream.Stream;

public enum SearchOperation {
    EQUALITY(":"), NEGATION("!"), GREATER_THAN(">"), LESS_THAN("<"), LIKE("~");

    private final String symbol;

    SearchOperation(final String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the symbol of operation
     */
    private String getSymbol() {
        return symbol;
    }

    public static String[] operationSet() {
        return Stream.of(SearchOperation.values()).map(SearchOperation::getSymbol).toArray(String[]::new);
    }

    public static Optional<SearchOperation> getSimpleOperation(final String input) {
        SearchOperation operation;
        switch (input) {
        case ":":
            operation = EQUALITY;
            break;
        case "!":
            operation = NEGATION;
            break;
        case ">":
            operation = GREATER_THAN;
            break;
        case "<":
            operation = LESS_THAN;
            break;
        case "~":
            operation = LIKE;
            break;
        default:
            operation = null;
            break;
        }
        return Optional.ofNullable(operation);

    }
}
