package com.hojo.fenix.warehouse.services;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListItemsRequest;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListRequest;

public interface WarehouseShoppingListService {


    /**
     * Method to return a shopping list
     *
     * @param id shopping list id
     * @return The shopping list entity
     */
    ShoppingListEntity getShoppingListById(Long id);

    /**
     * Return a container with list witch contains shopping list filtered
     *
     * @param filter Search filter
     * @param limit pagination limit request
     * @param offset pagination offset request
     * @return Container with shopping list entities
     */
    ContainerList<ShoppingListEntity> getShoppingLists(String filter, Integer limit, Long offset);

    /**
     * The method create or update shopping list data
     *
     * @param shoppingListRequest The request with information about list
     * @return The shopping List entity
     */
    ShoppingListEntity saveShoppingList(ShoppingListRequest shoppingListRequest);

    /**
     * Method to update items from a list. List must be created previously.
     *
     * @param shoppingListItems The request with shopping list id and items
     * @return The shopping List entity
     */
    ShoppingListEntity editShoppingListItems(ShoppingListItemsRequest shoppingListItems);

    /**
     * Method to delete the shopping list and items
     *
     * @param id The shopping list id
     */
    void deleteShoppingList(Long id);


}
