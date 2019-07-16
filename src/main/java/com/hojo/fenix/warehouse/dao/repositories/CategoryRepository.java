package com.hojo.fenix.warehouse.dao.repositories;

import com.hojo.fenix.warehouse.domain.entities.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author hojo
 *
 */
public interface CategoryRepository
        extends CrudRepository<ProductCategoryEntity, String>, JpaSpecificationExecutor<ProductCategoryEntity> {

}
