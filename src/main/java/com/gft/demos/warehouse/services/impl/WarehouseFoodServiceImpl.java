package com.gft.demos.warehouse.services.impl;

import com.gft.demos.warehouse.dao.WarehouseFoodDao;
import com.gft.demos.warehouse.domain.cdm.OffsetPagination;
import com.gft.demos.warehouse.domain.cdm.OffsetPaginationRequest;
import com.gft.demos.warehouse.domain.mappers.FoodMapper;
import com.gft.demos.warehouse.domain.requests.FoodRequest;
import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.FoodEntity;
import com.gft.demos.warehouse.services.WarehouseFoodService;
import com.gft.demos.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author hojo
 */
@Service
class WarehouseFoodServiceImpl implements WarehouseFoodService {

    private final WarehouseFoodDao warehouseDao;
    private final QueryLanguajeComponentImpl<FoodEntity> qlFood;
    private final FoodMapper foodMapper;

    public WarehouseFoodServiceImpl(final WarehouseFoodDao warehouseFoodDao, final FoodMapper foodMapper,
                                    final QueryLanguajeComponentImpl<FoodEntity> qlFood) {
        this.warehouseDao = warehouseFoodDao;
        this.foodMapper = foodMapper;
        this.qlFood = qlFood;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity createFood(FoodRequest food) {
        // Check manually if categories and subcategories exist?
        return warehouseDao.createFood(foodMapper.mapToInner(food));
    }

    @Override
    public FoodEntity updateFood(FoodRequest food) {
        // Check manually if categories and subcategories exist?
        return warehouseDao.updateFood(foodMapper.mapToInner(food));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<FoodEntity> searchFoods(final String filter, Integer limit, Long offset) {
        if (offset != null && limit != null) {
            final Pageable pageable = OffsetPaginationRequest.of(limit, offset);
            final Page<FoodEntity> foodEntityPage = warehouseDao.searchFoods(qlFood.parse(filter), pageable);
            final OffsetPagination offsetPagination = new OffsetPagination(limit, offset, foodEntityPage.getTotalElements());
            return new ContainerList<>(foodEntityPage.get().collect(Collectors.toList()), offsetPagination);

        } else {
            return new ContainerList<>(warehouseDao.searchFoods(qlFood.parse(filter)));
        }


    }

}
