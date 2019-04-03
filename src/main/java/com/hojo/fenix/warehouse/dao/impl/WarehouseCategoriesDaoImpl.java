package com.hojo.fenix.warehouse.dao.impl;

import com.hojo.fenix.warehouse.dao.WarehouseCategoriesDao;
import com.hojo.fenix.warehouse.dao.repositories.CategoryRepository;
import com.hojo.fenix.warehouse.dao.repositories.SubCategoryRepository;
import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hojo.fenix.warehouse.utils.exceptions.ExceptionConstants.*;

@Repository
@Transactional
class WarehouseCategoriesDaoImpl implements WarehouseCategoriesDao {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;


    public WarehouseCategoriesDaoImpl(final CategoryRepository categoryRepository,
                                      final SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodCategoryEntity createCategory(final FoodCategoryEntity foodCategoryEntity) {
        if (categoryRepository.existsById(foodCategoryEntity.getName())) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE, ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return categoryRepository.save(foodCategoryEntity);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodCategoryEntity updateCategory(final FoodCategoryEntity foodCategoryEntity) {
        this.getCategory(foodCategoryEntity.getName());
        return categoryRepository.save(foodCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodCategoryEntity getCategory(final String name) {
        return categoryRepository.findById(name).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, name)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCategory(final String name) {
        final FoodCategoryEntity category = this.getCategory(name);
        category.getSubcategories().forEach(subCategoryEntity -> {
            subCategoryEntity.setCategoryName(null);
            subCategoryRepository.save(subCategoryEntity);
        });
        categoryRepository.deleteById(category.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodCategoryEntity> searchCategories(final Specification<FoodCategoryEntity> spec) {
        return categoryRepository.findAll(spec);
    }

    @Override
    public Page<FoodCategoryEntity> searchCategories(Specification<FoodCategoryEntity> spec, Pageable pageable) {
        return categoryRepository.findAll(spec, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodSubCategoryEntity createSubCategory(final FoodSubCategoryEntity foodSubCategoryEntity) {
        if (subCategoryRepository.existsById(foodSubCategoryEntity.getName())) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE, ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return subCategoryRepository.save(foodSubCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodSubCategoryEntity updateSubCategory(final FoodSubCategoryEntity foodSubCategoryEntity) {
        this.getSubcategory(foodSubCategoryEntity.getName());
        return subCategoryRepository.save(foodSubCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodSubCategoryEntity getSubcategory(final String name) {
        return subCategoryRepository.findById(name).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, name)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSubcategory(String name) {
        FoodSubCategoryEntity foodSubCategoryEntity = getSubcategory(name);
        if (foodSubCategoryEntity.getCategoryName() != null) {
            FoodCategoryEntity foodCategoryEntity = categoryRepository.findById(foodSubCategoryEntity.getCategoryName()).get();
            foodCategoryEntity.getSubcategories().removeIf(sc -> sc.getCategoryName().equals(name));
            categoryRepository.save(foodCategoryEntity);
        }
        subCategoryRepository.deleteById(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodSubCategoryEntity> searchSubCategories(final Specification<FoodSubCategoryEntity> specification) {
        return subCategoryRepository.findAll(specification);
    }



}
