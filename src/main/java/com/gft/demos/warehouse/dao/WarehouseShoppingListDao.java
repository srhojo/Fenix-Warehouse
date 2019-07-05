package com.gft.demos.warehouse.dao;

import com.gft.demos.warehouse.domain.entities.ShoppingListEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface WarehouseShoppingListDao {

    ShoppingListEntity getById(Long id);

    List<ShoppingListEntity> search(Specification<ShoppingListEntity> specification);

    Page<ShoppingListEntity> search(Specification<ShoppingListEntity> specification, Pageable pageable);

    ShoppingListEntity create(ShoppingListEntity shoppingListEntity);

    ShoppingListEntity update(ShoppingListEntity shoppingListEntity);

    void delete(Long id);
}
