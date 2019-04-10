package com.hojo.fenix.warehouse.services;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.ProductCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.ProductSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.CategoryRequest;
import com.hojo.fenix.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.domain.requests.SubCategoryRequest;

public interface WarehouseCategoriesServices {

    ProductCategoryEntity createCategory(CategoryRequest categoryEntities);

    ProductCategoryEntity updateCategory(CategoryRequest categoryEntities);

    ProductCategoryEntity updateSubCategoriesToCategory(CategoryUpdateSubCategoriesRequest request);

    ContainerList<ProductCategoryEntity> getCategories(String filter, Integer limit, Long offset);

    void deleteCategory(String name);

    ProductSubCategoryEntity createSubCategory(SubCategoryRequest subCategoryRequest);

    ProductSubCategoryEntity updateSubCategory(SubCategoryRequest subCategoryRequest);

    ContainerList<ProductSubCategoryEntity> searchSubCategories(String filter, Integer limit, Long offset);
}
