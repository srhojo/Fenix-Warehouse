package com.gft.demos.warehouse.dao.repositories;

import com.gft.demos.warehouse.domain.entities.ProductSubCategoryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SubCategoryRepository
        extends CrudRepository<ProductSubCategoryEntity, String>, JpaSpecificationExecutor<ProductSubCategoryEntity> {

}
