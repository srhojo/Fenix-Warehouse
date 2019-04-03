package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.dao.WarehouseFoodDao;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.QuantityEmbeddableEntity;
import com.hojo.fenix.warehouse.domain.requests.FoodRequest;
import com.hojo.fenix.warehouse.services.WarehouseFoodService;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author hojo
 */
@Service
class WarehouseFoodServiceImpl implements WarehouseFoodService {

    private final WarehouseFoodDao warehouseDao;
    private final QueryLanguajeComponentImpl<FoodEntity> qlFood;

    public WarehouseFoodServiceImpl(final WarehouseFoodDao warehouseFoodDao,
                                    final QueryLanguajeComponentImpl<FoodEntity> qlFood) {
        this.warehouseDao = warehouseFoodDao;
        this.qlFood = qlFood;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity createFood(FoodRequest food) {

        // Check manually if categories and subcategories exist?
        foodMapper(food);
        return warehouseDao.createFood(foodMapper(food));
    }

    @Override
    public FoodEntity updateFood(FoodRequest food) {

        // Check manually if categories and subcategories exist?
        return warehouseDao.updateFood(foodMapper(food));
    }

    private FoodEntity foodMapper(FoodRequest food) {
        //Extract to map??
        FoodEntity entity = new FoodEntity();
        entity.setName(food.getName());
        entity.setDescription(food.getDescription());
        entity.setCategory(new FoodCategoryEntity());
        entity.getCategory().setName(food.getCategoryName());
        if (food.getSubCategoryName() != null) {
            entity.setSubCategory(new FoodSubCategoryEntity());
            entity.getSubCategory().setName(food.getSubCategoryName());
        }
        QuantityEmbeddableEntity quantityEntity = new QuantityEmbeddableEntity();
        quantityEntity.setUnities(food.getQuantity().getUnities());
        quantityEntity.setValue(food.getQuantity().getValue());

        entity.setQuantity(quantityEntity);
        entity.setLastUpdatedDate(LocalDateTime.now());
        entity.setExpirationDate(food.getExpirationDate());
        return entity;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<FoodEntity> searchFoods(final String filter) {
        return new ContainerList<>(warehouseDao.searchFoods(qlFood.parse(filter)));
    }

}
