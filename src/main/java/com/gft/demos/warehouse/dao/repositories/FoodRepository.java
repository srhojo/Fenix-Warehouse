package com.gft.demos.warehouse.dao.repositories;

import com.gft.demos.warehouse.domain.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<FoodEntity, String>, JpaSpecificationExecutor<FoodEntity> {

}
