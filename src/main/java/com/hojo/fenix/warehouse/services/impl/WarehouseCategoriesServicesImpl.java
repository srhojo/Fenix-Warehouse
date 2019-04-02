package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.dao.WarehouseDao;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.services.WarehouseCategoriesServices;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseCategoriesServicesImpl implements WarehouseCategoriesServices {

    private final WarehouseDao warehouseDao;
    private final QueryLanguajeComponentImpl<FoodCategoryEntity> qlCategory;
    private final QueryLanguajeComponentImpl<FoodSubCategoryEntity> qlSubCategory;

    public WarehouseCategoriesServicesImpl(final WarehouseDao warehouseDao,
                                           final QueryLanguajeComponentImpl<FoodCategoryEntity> qlCategory,
                                           final QueryLanguajeComponentImpl<FoodSubCategoryEntity> qlSubCategory) {
        this.warehouseDao = warehouseDao;
        this.qlCategory = qlCategory;
        this.qlSubCategory = qlSubCategory;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<FoodCategoryEntity> createCategories(final List<FoodCategoryEntity> categoryEntities) {
        return new ContainerList<>(warehouseDao.createCategories(categoryEntities));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCategory(String name) {
        warehouseDao.deleteCategory(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<FoodCategoryEntity> updateCategories(List<FoodCategoryEntity> categoryEntities) {
        return new ContainerList<>(warehouseDao.updateCategories(categoryEntities));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public FoodCategoryEntity updateSubCategoriesToCategory(CategoryUpdateSubCategoriesRequest request) {
        FoodCategoryEntity foodCategoryEntity = warehouseDao.getCategory(request.getCategoryId());

        List<FoodSubCategoryEntity> subCategoryEntities = new ArrayList<>();
        request.getSubcategoryIds().forEach(subcategoryId -> subCategoryEntities.add(warehouseDao.getSubcategory(subcategoryId)));

        foodCategoryEntity.setSubcategories(subCategoryEntities);

        return warehouseDao.updateCategory(foodCategoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<FoodCategoryEntity> getCategories(final String filter) {
        return new ContainerList<>(warehouseDao.searchCategories(qlCategory.parse(filter)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<FoodSubCategoryEntity> createSubCategories(final List<FoodSubCategoryEntity> subCategoryEntities) {
        return new ContainerList<>(warehouseDao.createSubCategories(subCategoryEntities));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<FoodSubCategoryEntity> searchSubCategories(final String filter) {
        return new ContainerList<>(warehouseDao.searchSubCategories(qlSubCategory.parse(filter)));
    }
}
