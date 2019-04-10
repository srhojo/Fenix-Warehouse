package com.hojo.fenix.warehouse.services;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.requests.FoodRequest;

/**
 * @author hojo
 */
public interface WarehouseFoodService {

    FoodEntity createFood(FoodRequest food);

    FoodEntity updateFood(FoodRequest food);

    ContainerList<FoodEntity> searchFoods(String filter, Integer limit, Long offset);

}
