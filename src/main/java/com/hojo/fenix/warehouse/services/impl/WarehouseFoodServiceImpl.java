package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.dao.WarehouseDao;
import com.hojo.fenix.warehouse.domain.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.domain.ContainerEntities;
import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;
import com.hojo.fenix.warehouse.services.WarehouseFoodService;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hojo
 */
@Service
public class WarehouseFoodServiceImpl implements WarehouseFoodService {

    private final WarehouseDao warehouseDao;
    private final QueryLanguajeComponentImpl<FoodCategoryEntity> qlCategory;
    private final QueryLanguajeComponentImpl<FoodSubCategoryEntity> qlSubCategory;
    private final QueryLanguajeComponentImpl<FoodEntity> qlFood;

    public WarehouseFoodServiceImpl(final WarehouseDao warehouseDao,
                                    final QueryLanguajeComponentImpl<FoodCategoryEntity> qlCategory,
                                    final QueryLanguajeComponentImpl<FoodSubCategoryEntity> qlSubCategory,
                                    final QueryLanguajeComponentImpl<FoodEntity> qlFood) {
        this.warehouseDao = warehouseDao;
        this.qlCategory = qlCategory;
        this.qlSubCategory = qlSubCategory;
        this.qlFood = qlFood;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerEntities<FoodCategoryEntity> createCategories(final List<FoodCategoryEntity> categoryEntities) {
        return new ContainerEntities<>(warehouseDao.createCategories(categoryEntities));
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
    public ContainerEntities<FoodCategoryEntity> updateCategories(List<FoodCategoryEntity> categoryEntities) {
        return new ContainerEntities<>(warehouseDao.updateCategories(categoryEntities));
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
    public ContainerEntities<FoodCategoryEntity> getCategories(final String filter) {
        return new ContainerEntities<>(warehouseDao.searchCategories(qlCategory.parse(filter)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerEntities<FoodSubCategoryEntity> createSubCategories(final List<FoodSubCategoryEntity> subCategoryEntities) {
        return new ContainerEntities<>(warehouseDao.createSubCategories(subCategoryEntities));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerEntities<FoodSubCategoryEntity> searchSubCategories(final String filter) {
        return new ContainerEntities<>(warehouseDao.searchSubCategories(qlSubCategory.parse(filter)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity createFood(FoodEntity food) {
        return warehouseDao.createFood(food);
    }

    @Override
    public FoodEntity updateFood(FoodEntity food) {
        return warehouseDao.updateFood(food);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerEntities<FoodEntity> searchFoods(final String filter) {
        return new ContainerEntities<>(warehouseDao.searchFoods(qlFood.parse(filter)));
    }

}
