package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.dao.WarehouseShoppingListDao;
import com.hojo.fenix.warehouse.domain.cdm.OffsetPagination;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import com.hojo.fenix.warehouse.domain.mappers.ShoppingListItemMapper;
import com.hojo.fenix.warehouse.domain.mappers.ShoppingListMapper;
import com.hojo.fenix.warehouse.services.WarehouseShoppingListService;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.cdm.OffsetPaginationRequest;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListItemsRequest;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListRequest;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Service
class WarehouseShoppingListServiceImpl implements WarehouseShoppingListService {


    private final WarehouseShoppingListDao warehouseShoppingListDao;
    private final ShoppingListMapper shoppingListMapper;
    private final ShoppingListItemMapper shoppingListItemMapper;
    private final QueryLanguajeComponentImpl<ShoppingListEntity> qlShoppingList;

    public WarehouseShoppingListServiceImpl(WarehouseShoppingListDao warehouseShoppingListDao, ShoppingListMapper shoppingListMapper, ShoppingListItemMapper shoppingListItemMapper, QueryLanguajeComponentImpl<ShoppingListEntity> qlShoppingList) {
        this.warehouseShoppingListDao = warehouseShoppingListDao;
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingListItemMapper = shoppingListItemMapper;
        this.qlShoppingList = qlShoppingList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity getShoppingListById(Long id) {
        return warehouseShoppingListDao.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<ShoppingListEntity> getShoppingLists(String filter, Integer limit, Long offset) {
        if (offset != null && limit != null) {
            Pageable pageable = OffsetPaginationRequest.of(limit, offset);
            final Page<ShoppingListEntity> shoppingListEntityPage = warehouseShoppingListDao.search(qlShoppingList.parse(filter), pageable);
            final OffsetPagination offsetPagination = new OffsetPagination(limit, offset, shoppingListEntityPage.getTotalElements());
            return new ContainerList<>(shoppingListEntityPage.get().collect(Collectors.toList()), offsetPagination);

        } else {
            return new ContainerList<>(warehouseShoppingListDao.search(qlShoppingList.parse(filter)));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity saveShoppingList(ShoppingListRequest shoppingListRequest) {
        final ShoppingListEntity shoppingListEntity = shoppingListMapper.mapToInner(shoppingListRequest);
        return StringUtils.isEmpty(shoppingListEntity.getId()) ? warehouseShoppingListDao.create(shoppingListEntity) : warehouseShoppingListDao.update(shoppingListEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity editShoppingListItems(ShoppingListItemsRequest shoppingListItems) {
        ShoppingListEntity entity = warehouseShoppingListDao.getById(shoppingListItems.getShoppingListId());
        entity.setItems(shoppingListItemMapper.mapToInner(shoppingListItems));
        return warehouseShoppingListDao.update(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteShoppingList(Long id) {
        warehouseShoppingListDao.delete(id);
    }
}
