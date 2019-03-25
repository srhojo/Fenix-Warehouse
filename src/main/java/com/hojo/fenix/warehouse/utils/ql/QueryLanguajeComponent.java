package com.hojo.fenix.warehouse.utils.ql;

import org.springframework.data.jpa.domain.Specification;

public interface QueryLanguajeComponent<T> {

    Specification<T> parse(final String filter);

}
