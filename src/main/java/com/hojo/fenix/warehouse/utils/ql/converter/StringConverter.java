package com.hojo.fenix.warehouse.utils.ql.converter;

@FunctionalInterface
public interface StringConverter<U> {

    U map(final String value, final Class<U> clazz);
}
