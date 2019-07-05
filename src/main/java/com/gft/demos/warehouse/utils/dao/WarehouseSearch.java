package com.gft.demos.warehouse.utils.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Specification search interface
 *
 * @param <T> Entity
 */
public interface WarehouseSearch<T> {

    /**
     * Searching method by specification (qwery languge).
     *
     * @param spec Specification to search
     * @return List of entities
     */
    List<T> search(Specification<T> spec);

    /**
     * Searching method by specification and pagination properties
     *
     * @param spec Specification to search
     * @param pageable Pageable properties
     * @return Page of entities
     */
    Page<T> search(Specification<T> spec, Pageable pageable);
}
