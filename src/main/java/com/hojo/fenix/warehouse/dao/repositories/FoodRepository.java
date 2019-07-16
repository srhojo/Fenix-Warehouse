package com.hojo.fenix.warehouse.dao.repositories;

import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<FoodEntity, String>, JpaSpecificationExecutor<FoodEntity> {

}
