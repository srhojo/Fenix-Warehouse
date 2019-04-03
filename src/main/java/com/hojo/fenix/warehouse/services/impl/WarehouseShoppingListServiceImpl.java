package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.dao.WarehouseShoppingListDao;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.QuantityEmbeddableEntity;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListItemsEmbeddableIdEntity;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListItemsEntity;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListItemsRequest;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListRequest;
import com.hojo.fenix.warehouse.services.WarehouseShoppingListService;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
class WarehouseShoppingListServiceImpl implements WarehouseShoppingListService {


    private final WarehouseShoppingListDao warehouseShoppingListDao;
    private final QueryLanguajeComponentImpl<ShoppingListEntity> qlShoppingList;

    public WarehouseShoppingListServiceImpl(WarehouseShoppingListDao warehouseShoppingListDao, final QueryLanguajeComponentImpl<ShoppingListEntity> qlShoppingList) {
        this.warehouseShoppingListDao = warehouseShoppingListDao;
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
    public ContainerList<ShoppingListEntity> getShoppingLists(String filter) {
        return new ContainerList<>(warehouseShoppingListDao.search(qlShoppingList.parse(filter)));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity saveShoppingList(ShoppingListRequest shoppingListRequest) {

        //Map
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(shoppingListRequest.getId());
        shoppingListEntity.setName(shoppingListRequest.getName());
        shoppingListEntity.setDescription(shoppingListRequest.getDescription());
        shoppingListEntity.setLastUpdatedDate(LocalDateTime.now());

        return StringUtils.isEmpty(shoppingListEntity.getId()) ? warehouseShoppingListDao.create(shoppingListEntity) : warehouseShoppingListDao.update(shoppingListEntity);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity editShoppingListItems(ShoppingListItemsRequest shoppingListItems) {
        ShoppingListEntity entity = warehouseShoppingListDao.getById(shoppingListItems.getShoppingListId());

        Map<String, ShoppingListItemsEntity> mapItems = new HashMap<>();
        shoppingListItems.getItems().forEach(item -> {

            //Map el id
            ShoppingListItemsEntity itemEntity = new ShoppingListItemsEntity();
            ShoppingListItemsEmbeddableIdEntity shoppingListItemsEmbeddableIdEntity = new ShoppingListItemsEmbeddableIdEntity();
            shoppingListItemsEmbeddableIdEntity.setProductId(item.getProductId());
            shoppingListItemsEmbeddableIdEntity.setShoppingListId(entity.getId());
            itemEntity.setId(shoppingListItemsEmbeddableIdEntity);


            //Map quantity value
            QuantityEmbeddableEntity quantityEmbeddableEntity = new QuantityEmbeddableEntity();
            quantityEmbeddableEntity.setValue(item.getQuantity().getValue());
            quantityEmbeddableEntity.setUnities(item.getQuantity().getUnities());
            itemEntity.setQuantity(quantityEmbeddableEntity);


            mapItems.put(item.getProductId(), itemEntity);
        });

        entity.setItems(mapItems);


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
