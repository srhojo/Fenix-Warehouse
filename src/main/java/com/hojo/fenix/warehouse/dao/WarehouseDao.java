package com.hojo.fenix.warehouse.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.hojo.fenix.warehouse.domain.entities.CategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.SubCategoryEntity;

/**
 * @author hojo
 *
 */
public interface WarehouseDao {

    CategoryEntity createCategory(CategoryEntity categoryEntity);

    CategoryEntity updateCategory(CategoryEntity categoryEntity);

    CategoryEntity getCategory(Long id);

    List<CategoryEntity> searchCategories(Specification<CategoryEntity> spec);

    void deleteCategory(Long id);

    default List<CategoryEntity> createCategories(final List<CategoryEntity> categories) {
        return categories.stream().map(this::createCategory).collect(Collectors.toList());
    }

    default List<CategoryEntity> updateCategories(final List<CategoryEntity> categories) {
        return categories.stream().map(this::updateCategory).collect(Collectors.toList());
    }

    SubCategoryEntity createSubCategory(SubCategoryEntity subCategoryEntity);

    SubCategoryEntity updateSubCategory(SubCategoryEntity subCategoryEntity);

    SubCategoryEntity getSubcategory(Long id);

    void deleteSubcategory(Long id);

    List<SubCategoryEntity> searchSubCategories(Specification<SubCategoryEntity> specification);

    default List<SubCategoryEntity> createSubCategories(final List<SubCategoryEntity> subcategories) {
        return subcategories.stream().map(this::createSubCategory).collect(Collectors.toList());
    }

    default List<SubCategoryEntity> updateSubCategories(final List<SubCategoryEntity> subcategories) {
        return subcategories.stream().map(this::updateSubCategory).collect(Collectors.toList());
    }

    FoodEntity createFood(FoodEntity foodEntity);

    FoodEntity updateFood(FoodEntity foodEntity);

    FoodEntity getFood(String name);

    List<FoodEntity> searchFoods(Specification<FoodEntity> specification);

    default List<FoodEntity> createFoods(final List<FoodEntity> foods) {
        return foods.stream().map(this::createFood).collect(Collectors.toList());
    }

    default List<FoodEntity> updateFoods(final List<FoodEntity> foods) {
        return foods.stream().map(this::updateFood).collect(Collectors.toList());
    }

}
