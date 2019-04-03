package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.dao.WarehouseCategoriesDao;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.services.WarehouseCategoriesServices;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
class WarehouseCategoriesServicesImpl implements WarehouseCategoriesServices {

    private final WarehouseCategoriesDao warehouseDao;
    private final QueryLanguajeComponentImpl<FoodCategoryEntity> qlCategory;
    private final QueryLanguajeComponentImpl<FoodSubCategoryEntity> qlSubCategory;

    public WarehouseCategoriesServicesImpl(final WarehouseCategoriesDao warehouseDao,
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

        Pageable pageable = PageRequest.of(0,2);
        Page<FoodCategoryEntity> page = warehouseDao.searchCategories(qlCategory.parse(filter),pageable);
        return new ContainerList<>(page.get().collect(Collectors.toList()));
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
