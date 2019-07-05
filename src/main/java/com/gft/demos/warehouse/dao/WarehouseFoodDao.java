package com.gft.demos.warehouse.dao;

import com.gft.demos.warehouse.domain.entities.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public interface WarehouseFoodDao {

    FoodEntity createFood(FoodEntity foodEntity);

    FoodEntity updateFood(FoodEntity foodEntity);

    FoodEntity getFood(String name);

    List<FoodEntity> searchFoods(Specification<FoodEntity> specification);

    Page<FoodEntity> searchFoods(Specification<FoodEntity> specification, Pageable pageable);

    default List<FoodEntity> createFoods(final List<FoodEntity> foods) {
        return foods.stream().map(this::createFood).collect(Collectors.toList());
    }

    default List<FoodEntity> updateFoods(final List<FoodEntity> foods) {
        return foods.stream().map(this::updateFood).collect(Collectors.toList());
    }
}
