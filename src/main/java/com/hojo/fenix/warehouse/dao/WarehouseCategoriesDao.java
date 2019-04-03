package com.hojo.fenix.warehouse.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;

/**
 * @author hojo
 *
 */
public interface WarehouseCategoriesDao {

    FoodCategoryEntity createCategory(FoodCategoryEntity foodCategoryEntity);

    FoodCategoryEntity updateCategory(FoodCategoryEntity foodCategoryEntity);

    FoodCategoryEntity getCategory(String name);

    List<FoodCategoryEntity> searchCategories(Specification<FoodCategoryEntity> spec);

    Page<FoodCategoryEntity> searchCategories(Specification<FoodCategoryEntity> spec, Pageable pageable);

    void deleteCategory(String name);

    default List<FoodCategoryEntity> createCategories(final List<FoodCategoryEntity> categories) {
        return categories.stream().map(this::createCategory).collect(Collectors.toList());
    }

    default List<FoodCategoryEntity> updateCategories(final List<FoodCategoryEntity> categories) {
        return categories.stream().map(this::updateCategory).collect(Collectors.toList());
    }

    FoodSubCategoryEntity createSubCategory(FoodSubCategoryEntity foodSubCategoryEntity);

    FoodSubCategoryEntity updateSubCategory(FoodSubCategoryEntity foodSubCategoryEntity);

    FoodSubCategoryEntity getSubcategory(String name);

    void deleteSubcategory(String name);

    List<FoodSubCategoryEntity> searchSubCategories(Specification<FoodSubCategoryEntity> specification);

    default List<FoodSubCategoryEntity> createSubCategories(final List<FoodSubCategoryEntity> subcategories) {
        return subcategories.stream().map(this::createSubCategory).collect(Collectors.toList());
    }

    default List<FoodSubCategoryEntity> updateSubCategories(final List<FoodSubCategoryEntity> subcategories) {
        return subcategories.stream().map(this::updateSubCategory).collect(Collectors.toList());
    }



}
