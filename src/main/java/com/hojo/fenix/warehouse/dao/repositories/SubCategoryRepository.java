package com.hojo.fenix.warehouse.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubCategoryRepository
        extends  CrudRepository<FoodSubCategoryEntity, String>, JpaSpecificationExecutor<FoodSubCategoryEntity> {

}
