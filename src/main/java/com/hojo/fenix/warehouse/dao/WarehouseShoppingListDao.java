package com.hojo.fenix.warehouse.dao;

import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface WarehouseShoppingListDao {

    ShoppingListEntity getById(Long id);

    List<ShoppingListEntity> search(Specification<ShoppingListEntity> specification);

    ShoppingListEntity create(ShoppingListEntity shoppingListEntity);

    ShoppingListEntity update(ShoppingListEntity shoppingListEntity);

    void delete(Long id);
}
