package com.hojo.fenix.warehouse.services;

import java.util.List;

import com.hojo.fenix.warehouse.domain.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.domain.ContainerEntities;
import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;

/**
 * @author hojo
 *
 */
public interface WarehouseFoodService {

    ContainerEntities<FoodCategoryEntity> createCategories(List<FoodCategoryEntity> categoryEntities);

    void deleteCategory(String name);

    ContainerEntities<FoodCategoryEntity> updateCategories(List<FoodCategoryEntity> categoryEntities);

    FoodCategoryEntity updateSubCategoriesToCategory(CategoryUpdateSubCategoriesRequest request);

    ContainerEntities<FoodCategoryEntity> getCategories(String filter);

    ContainerEntities<FoodSubCategoryEntity> createSubCategories(List<FoodSubCategoryEntity> subCategoryEntities);

    ContainerEntities<FoodSubCategoryEntity> searchSubCategories(String filter);

    FoodEntity createFood(FoodEntity food);

    FoodEntity updateFood(FoodEntity food);

    ContainerEntities<FoodEntity> searchFoods(String filter);

}
