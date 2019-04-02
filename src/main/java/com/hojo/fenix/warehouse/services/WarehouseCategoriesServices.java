package com.hojo.fenix.warehouse.services;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;

import java.util.List;

public interface WarehouseCategoriesServices {

    ContainerList<FoodCategoryEntity> createCategories(List<FoodCategoryEntity> categoryEntities);

    void deleteCategory(String name);

    ContainerList<FoodCategoryEntity> updateCategories(List<FoodCategoryEntity> categoryEntities);

    FoodCategoryEntity updateSubCategoriesToCategory(CategoryUpdateSubCategoriesRequest request);

    ContainerList<FoodCategoryEntity> getCategories(String filter);

    ContainerList<FoodSubCategoryEntity> createSubCategories(List<FoodSubCategoryEntity> subCategoryEntities);

    ContainerList<FoodSubCategoryEntity> searchSubCategories(String filter);
}
