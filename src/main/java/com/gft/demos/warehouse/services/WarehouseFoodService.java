package com.gft.demos.warehouse.services;

import com.gft.demos.warehouse.domain.requests.FoodRequest;
import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.FoodEntity;

/**
 * @author hojo
 */
public interface WarehouseFoodService {

    FoodEntity createFood(FoodRequest food);

    FoodEntity updateFood(FoodRequest food);

    ContainerList<FoodEntity> searchFoods(String filter, Integer limit, Long offset);

}
