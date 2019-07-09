package com.gft.demos.warehouse.utils.ql.converter;


@FunctionalInterface
public interface Converter {

    <U> Object convert(final String value, final Class<? extends U> type);

}
