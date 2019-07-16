package com.hojo.fenix.warehouse.utils.mappers;

/**
 * @param <I> Inner Object
 * @param <O> Outer Object
 * @author Hojo
 * <p>
 * Interface who extend from InnerMapper and OuterMapper interfaces
 */
public interface Mapper<I, O> extends InnerMapper<I, O>, OuterMapper<O, I> {
}
