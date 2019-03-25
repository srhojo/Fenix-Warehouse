package com.hojo.fenix.warehouse.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.hojo.fenix.warehouse.domain.entities.CategoryEntity;

/**
 * @author hojo
 *
 */
public interface CategoryRepository
        extends CrudRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {

}
