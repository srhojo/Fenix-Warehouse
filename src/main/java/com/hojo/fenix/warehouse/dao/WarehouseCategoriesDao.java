package com.hojo.fenix.warehouse.dao;

import com.hojo.fenix.warehouse.domain.entities.ProductCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.ProductSubCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hojo
 *
 */
public interface WarehouseCategoriesDao {

    ProductCategoryEntity createCategory(ProductCategoryEntity productCategoryEntity);

    ProductCategoryEntity updateCategory(ProductCategoryEntity productCategoryEntity);

    ProductCategoryEntity getCategory(String name);

    List<ProductCategoryEntity> searchCategories(Specification<ProductCategoryEntity> spec);

    Page<ProductCategoryEntity> searchCategories(Specification<ProductCategoryEntity> spec, Pageable pageable);

    void deleteCategory(String name);

    default List<ProductCategoryEntity> createCategories(final List<ProductCategoryEntity> categories) {
        return categories.stream().map(this::createCategory).collect(Collectors.toList());
    }

    default List<ProductCategoryEntity> updateCategories(final List<ProductCategoryEntity> categories) {
        return categories.stream().map(this::updateCategory).collect(Collectors.toList());
    }

    ProductSubCategoryEntity createSubCategory(ProductSubCategoryEntity productSubCategoryEntity);

    ProductSubCategoryEntity updateSubCategory(ProductSubCategoryEntity productSubCategoryEntity);

    ProductSubCategoryEntity getSubcategory(String name);

    void deleteSubcategory(String name);

    List<ProductSubCategoryEntity> searchSubCategories(Specification<ProductSubCategoryEntity> specification);

    Page<ProductSubCategoryEntity> searchSubCategories(Specification<ProductSubCategoryEntity> specification, Pageable pageable);

    default List<ProductSubCategoryEntity> createSubCategories(final List<ProductSubCategoryEntity> subcategories) {
        return subcategories.stream().map(this::createSubCategory).collect(Collectors.toList());
    }

    default List<ProductSubCategoryEntity> updateSubCategories(final List<ProductSubCategoryEntity> subcategories) {
        return subcategories.stream().map(this::updateSubCategory).collect(Collectors.toList());
    }



}
