package com.gft.demos.warehouse.utils.ql;

import org.springframework.data.jpa.domain.Specification;

public interface QueryLanguajeComponent<T> {

    Specification<T> parse(final String filter);

}
