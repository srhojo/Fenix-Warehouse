package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.dao.WarehouseDao;
import com.hojo.fenix.warehouse.domain.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.domain.ContainerEntities;
import com.hojo.fenix.warehouse.domain.entities.CategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.SubCategoryEntity;
import com.hojo.fenix.warehouse.services.WarehouseService;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hojo
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseDao warehouseDao;
    private final QueryLanguajeComponentImpl<CategoryEntity> qlCategory;
    private final QueryLanguajeComponentImpl<SubCategoryEntity> qlSubCategory;
    private final QueryLanguajeComponentImpl<FoodEntity> qlFood;

    public WarehouseServiceImpl(final WarehouseDao warehouseDao,
                                final QueryLanguajeComponentImpl<CategoryEntity> qlCategory,
                                final QueryLanguajeComponentImpl<SubCategoryEntity> qlSubCategory,
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
    public ContainerEntities<CategoryEntity> createCategories(final List<CategoryEntity> categoryEntities) {
        return new ContainerEntities<>(warehouseDao.createCategories(categoryEntities));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCategory(Long id) {
        warehouseDao.deleteCategory(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerEntities<CategoryEntity> updateCategories(List<CategoryEntity> categoryEntities) {
        return new ContainerEntities<>(warehouseDao.updateCategories(categoryEntities));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryEntity updateSubCategoriesToCategory(CategoryUpdateSubCategoriesRequest request) {
        CategoryEntity categoryEntity = warehouseDao.getCategory(request.getCategoryId());

        List<SubCategoryEntity> subCategoryEntities = new ArrayList<>();
        request.getSubcategoryIds().forEach(subcategoryId -> subCategoryEntities.add(warehouseDao.getSubcategory(subcategoryId)));

        categoryEntity.setSubcategories(subCategoryEntities);

        return warehouseDao.updateCategory(categoryEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerEntities<CategoryEntity> getCategories(final String filter) {
        return new ContainerEntities<>(warehouseDao.searchCategories(qlCategory.parse(filter)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerEntities<SubCategoryEntity> createSubCategories(final List<SubCategoryEntity> subCategoryEntities) {
        return new ContainerEntities<>(warehouseDao.createSubCategories(subCategoryEntities));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerEntities<SubCategoryEntity> searchSubCategories(final String filter) {
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
