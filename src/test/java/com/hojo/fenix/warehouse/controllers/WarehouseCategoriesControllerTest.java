package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.ProductCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.ProductSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.CategoryRequest;
import com.hojo.fenix.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.domain.requests.SubCategoryRequest;
import com.hojo.fenix.warehouse.services.WarehouseCategoriesServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseCategoriesControllerTest {

    @InjectMocks
    private WarehouseCategoriesController warehouseCategoriesController;

    @Mock
    private WarehouseCategoriesServices warehouseCategoriesServices;

    @Test
    public void createCategory() {
        //Given
        CategoryRequest request = new CategoryRequest();
        given(warehouseCategoriesServices.createCategory(request)).willReturn(new ProductCategoryEntity());

        //When
        ProductCategoryEntity result = warehouseCategoriesController.createCategory(request);

        //Then
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).createCategory(request);
    }

    @Test
    public void updateCategory() {
        //Given
        CategoryRequest request = new CategoryRequest();
        given(warehouseCategoriesServices.updateCategory(request)).willReturn(new ProductCategoryEntity());

        //When
        ProductCategoryEntity result = warehouseCategoriesController.updateCategory(request);

        //THen
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).updateCategory(request);
    }

    @Test
    public void updateSubcategoriesToCategory() {
        //Given
        CategoryUpdateSubCategoriesRequest request = new CategoryUpdateSubCategoriesRequest();
        given((warehouseCategoriesServices.updateSubCategoriesToCategory(request))).willReturn(new ProductCategoryEntity());


        //When
        ProductCategoryEntity result = warehouseCategoriesController.updateSubcategoriesToCategory(request);

        //Then
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).updateSubCategoriesToCategory(request);
    }

    @Test
    public void getCategories() {
        //Given
        final String filter = "";
        final Integer limit = 2;
        final Long offset = 0l;
        given(warehouseCategoriesServices.getCategories(filter, limit, offset)).willReturn(new ContainerList<>());

        //When
        ContainerList result = warehouseCategoriesController.getCategories(filter, limit, offset);

        //Then
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).getCategories(filter, limit, offset);
    }

    @Test
    public void deleteCategory() {
        //Given
        final String name = "test";
        doNothing().when(warehouseCategoriesServices).deleteCategory(name);

        //When
        warehouseCategoriesController.deleteCategory(name);

        //Then
        verify(warehouseCategoriesServices, times(1)).deleteCategory(name);
    }

    @Test
    public void createSubCategoryEntity() {
        //Given
        SubCategoryRequest request = new SubCategoryRequest();
        given(warehouseCategoriesServices.createSubCategory(request)).willReturn(new ProductSubCategoryEntity());

        //When
        ProductSubCategoryEntity result = warehouseCategoriesController.createSubCategoryEntity(request);

        //THen
        assertNotNull(result);
        verify(warehouseCategoriesServices,times(1)).createSubCategory(request);
    }

    @Test
    public void updateSubCategory() {
        //Given
        SubCategoryRequest request = new SubCategoryRequest();
        given(warehouseCategoriesServices.updateSubCategory(request)).willReturn(new ProductSubCategoryEntity());

        //When
        ProductSubCategoryEntity result = warehouseCategoriesController.updateSubCategory(request);

        //THen
        assertNotNull(result);
        verify(warehouseCategoriesServices,times(1)).updateSubCategory((request));
    }

    @Test
    public void getSubCategories() {
        //Given
        final String filter = "";
        final Integer limit = 2;
        final Long offset = 0l;
        given(warehouseCategoriesServices.searchSubCategories(filter,limit,offset)).willReturn(new ContainerList<>());

        //When
        ContainerList result = warehouseCategoriesController.getSubCategories(filter,limit,offset);

        //THen
        assertNotNull(result);
        verify(warehouseCategoriesServices,times(1)).searchSubCategories(filter,limit,offset);
    }
}