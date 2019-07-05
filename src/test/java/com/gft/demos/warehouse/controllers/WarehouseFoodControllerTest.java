package com.gft.demos.warehouse.controllers;

import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.FoodEntity;
import com.gft.demos.warehouse.domain.requests.FoodRequest;
import com.gft.demos.warehouse.services.WarehouseFoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseFoodControllerTest {

     @InjectMocks
    private WarehouseFoodController warehouseFoodController;

     @Mock
     private WarehouseFoodService warehouseFoodService;

    @Test
    public void searchFood() {
        //Given
        final String filter="filter";
        final Integer limit=2;
        final Long offset= 0l;
        given(warehouseFoodService.searchFoods(filter,limit,offset)).willReturn(new ContainerList<>());


        //When
        ContainerList result = warehouseFoodController.searchFood(filter,limit,offset);

        //Then
        assertNotNull(result);
        verify(warehouseFoodService,times(1)).searchFoods(filter,limit,offset);
    }

    @Test
    public void createFood() {
        //Given
        FoodRequest request =new FoodRequest();
        given(warehouseFoodService.createFood(request)).willReturn(new FoodEntity());

        //When
        FoodEntity result = warehouseFoodController.createFood(request);

        //Then
        assertNotNull(result);
        verify(warehouseFoodService,times(1)).createFood(request);
    }

    @Test
    public void updateFood() {
        //Given
        String name = "food";
        FoodRequest request =new FoodRequest();
        given(warehouseFoodService.updateFood(request)).willReturn(new FoodEntity());

        //When
        FoodEntity result = warehouseFoodController.updateFood(name,request);

        //Then
        assertNotNull(result);
        verify(warehouseFoodService,times(1)).updateFood(request);
    }
}