package com.gft.demos.warehouse.dao.repositories;

import com.gft.demos.warehouse.domain.entities.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author hojo
 *
 */
public interface CategoryRepository
        extends CrudRepository<ProductCategoryEntity, String>, JpaSpecificationExecutor<ProductCategoryEntity> {

}
