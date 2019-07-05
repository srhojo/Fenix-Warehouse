package com.gft.demos.warehouse.dao.repositories;

import com.gft.demos.warehouse.domain.entities.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity,Long>, JpaSpecificationExecutor<ShoppingListEntity> {
}
