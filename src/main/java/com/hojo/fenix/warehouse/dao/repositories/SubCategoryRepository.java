package com.hojo.fenix.warehouse.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.hojo.fenix.warehouse.domain.entities.SubCategoryEntity;

public interface SubCategoryRepository
        extends CrudRepository<SubCategoryEntity, Long>, JpaSpecificationExecutor<SubCategoryEntity> {

}
