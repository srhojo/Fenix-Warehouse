package com.hojo.fenix.warehouse.dao.repositories;

import com.hojo.fenix.warehouse.domain.entities.ProductSubCategoryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SubCategoryRepository
        extends CrudRepository<ProductSubCategoryEntity, String>, JpaSpecificationExecutor<ProductSubCategoryEntity> {

}
