package com.hojo.fenix.warehouse.services;

import java.util.List;

import com.hojo.fenix.warehouse.domain.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.domain.ContainerEntities;
import com.hojo.fenix.warehouse.domain.entities.CategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.SubCategoryEntity;

/**
 * @author hojo
 *
 */
public interface WarehouseService {

    ContainerEntities<CategoryEntity> createCategories(List<CategoryEntity> categoryEntities);

    void deleteCategory(Long id);

    ContainerEntities<CategoryEntity> updateCategories(List<CategoryEntity> categoryEntities);

    CategoryEntity updateSubCategoriesToCategory(CategoryUpdateSubCategoriesRequest request);

    ContainerEntities<CategoryEntity> getCategories(String filter);

    ContainerEntities<SubCategoryEntity> createSubCategories(List<SubCategoryEntity> subCategoryEntities);

    ContainerEntities<SubCategoryEntity> searchSubCategories(String filter);

    FoodEntity createFood(FoodEntity food);

    FoodEntity updateFood(FoodEntity food);

    ContainerEntities<FoodEntity> searchFoods(String filter);

}
