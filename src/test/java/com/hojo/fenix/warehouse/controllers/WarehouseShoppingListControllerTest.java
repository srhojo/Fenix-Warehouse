package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListItemsRequest;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListRequest;
import com.hojo.fenix.warehouse.services.WarehouseShoppingListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseShoppingListControllerTest {

    @InjectMocks
    private WarehouseShoppingListController warehouseShoppingListController;

    @Mock
    private WarehouseShoppingListService warehouseShoppingListService;


    @Test
    public void getShoppingListById() {
        //Given
        final Long id = 1l;
        given(warehouseShoppingListService.getShoppingListById(id)).willReturn(new ShoppingListEntity());

        //When
        final ShoppingListEntity result = warehouseShoppingListController.getShoppingListById(id);

        //Then
        assertNotNull(result);
        verify(warehouseShoppingListService, times(1)).getShoppingListById(id);
    }

    @Test
    public void getShoppingLists() {
        //Given
        final String filter = "filter";
        final Integer limit = 2;
        final Long offset = 0l;
        given(warehouseShoppingListService.getShoppingLists(filter, limit, offset)).willReturn(new ContainerList<>());

        //When
        final ContainerList result = warehouseShoppingListController.getShoppingLists(filter, limit, offset);

        //Then
        assertNotNull(result);
        verify(warehouseShoppingListService, times(1)).getShoppingLists(filter, limit, offset);
    }

    @Test
    public void createShoppingList() {
        //Given
        ShoppingListRequest request = new ShoppingListRequest();
        given(warehouseShoppingListService.saveShoppingList(request)).willReturn(new ShoppingListEntity());

        //When
        ShoppingListEntity result = warehouseShoppingListController.createShoppingList(request);

        //Then
        assertNotNull(result);
        verify(warehouseShoppingListService,times(1)).saveShoppingList(request);
    }

    @Test
    public void editShoppingList() {
        //Given
        Long id= 1l;
        ShoppingListRequest request = new ShoppingListRequest();
        given(warehouseShoppingListService.saveShoppingList(request)).willReturn(new ShoppingListEntity());

        //When
        ShoppingListEntity result = warehouseShoppingListController.editShoppingList (id,request);

        //Then
        assertNotNull(result);
        assertEquals(id,request.getId());
        verify(warehouseShoppingListService,times(1)).saveShoppingList(request);
    }

    @Test
    public void deleteShoppingList() {
        //Given
        Long id = 1l;
        doNothing().when(warehouseShoppingListService).deleteShoppingList(id);

        //When
        warehouseShoppingListController.deleteShoppingList(id);

        //Then
        verify(warehouseShoppingListService,times(1)).deleteShoppingList(id);
    }

    @Test
    public void editShoppingListItems() {
        //Given
        Long id =1l;
        ShoppingListItemsRequest request = new ShoppingListItemsRequest();
        given(warehouseShoppingListService.editShoppingListItems(request)).willReturn(new ShoppingListEntity());

        //When
        ShoppingListEntity result = warehouseShoppingListController.editShoppingListItems(id,request);

        //Then
        assertNotNull(result);
        assertEquals(id,request.getShoppingListId());
        verify(warehouseShoppingListService,times(1)).editShoppingListItems(request);
    }
}