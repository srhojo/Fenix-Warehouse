package com.hojo.fenix.warehouse.dao.impl;

import com.hojo.fenix.warehouse.dao.WarehouseFoodDao;
import com.hojo.fenix.warehouse.dao.repositories.FoodRepository;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hojo.fenix.warehouse.utils.exceptions.ExceptionConstants.*;

@Repository
@Transactional
class WarehouseFoodDaoImpl implements WarehouseFoodDao {

    private final FoodRepository foodRepository;

    public WarehouseFoodDaoImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity createFood(final FoodEntity foodEntity) {
        if (foodRepository.existsById(foodEntity.getName())) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE, ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return save(foodEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity updateFood(final FoodEntity foodEntity) {
        this.getFood(foodEntity.getName());
        return save(foodEntity);
    }

    private FoodEntity save(FoodEntity foodEntity) {
        try{
            return foodRepository.save(foodEntity);
        }catch (Exception ex) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_SAVE_FOOD_CODE, String.format(ERRORS_DAO_SAVE_FOOD_MESSAGE,foodEntity.toString(),ex.getMessage()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity getFood(final String name) {
        return foodRepository.findById(name).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, name)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodEntity> searchFoods(final Specification<FoodEntity> specification) {
        return foodRepository.findAll(specification);
    }
}
