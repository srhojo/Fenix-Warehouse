package com.gft.demos.warehouse.services;

import com.gft.demos.warehouse.domain.requests.CategoryRequest;
import com.gft.demos.warehouse.domain.requests.SubCategoryRequest;
import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.ProductCategoryEntity;
import com.gft.demos.warehouse.domain.entities.ProductSubCategoryEntity;
import com.gft.demos.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;

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
