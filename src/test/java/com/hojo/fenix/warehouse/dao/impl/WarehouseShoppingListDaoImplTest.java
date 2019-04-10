package com.hojo.fenix.warehouse.dao.impl;

import com.hojo.fenix.warehouse.dao.repositories.ShoppingListRepository;
import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.hojo.fenix.warehouse.utils.exceptions.ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE;
import static com.hojo.fenix.warehouse.utils.exceptions.ExceptionConstants.ERRORS_DAO_SAVE_SHOPPING_LIST_CODE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class WarehouseShoppingListDaoImplTest {

    @InjectMocks
    @Spy
    private WarehouseShoppingListDaoImpl warehouseShoppingListDao;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Test
    public void getById() {
        //Given
        Long id = 1L;
        given(shoppingListRepository.findById(id)).willReturn(Optional.of(new ShoppingListEntity()));

        //When
        ShoppingListEntity result = warehouseShoppingListDao.getById(id);

        //Then
        assertNotNull(result);
    }

    @Test(expected = WarehouseException.class)
    public void getById_notFound() {
        //Given
        Long id = 1L;
        given(shoppingListRepository.findById(id)).willReturn(Optional.empty());

        //When
        warehouseShoppingListDao.getById(id);

        //Then
        //Exception
    }


    @Test
    public void search() {
        //Given
        Specification<ShoppingListEntity> specification = Mockito.mock(Specification.class);
        given(shoppingListRepository.findAll(specification)).willReturn(Collections.EMPTY_LIST);

        //When
        List<ShoppingListEntity> results = warehouseShoppingListDao.search(specification);

        //Then
        assertNotNull(results);
    }

    @Test
    public void create() {
        //Given
        ShoppingListEntity entity = new ShoppingListEntity();
        given(shoppingListRepository.save(entity)).willReturn(new ShoppingListEntity());

        //When
        ShoppingListEntity result = warehouseShoppingListDao.create(entity);

        //Then
        assertNotNull(result);
    }


    @Test
    public void create_updateException() {
        //Given
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(1L);

        //When
        try {

            warehouseShoppingListDao.create(entity);
        } catch (WarehouseException wex) {
            //Then
            assertEquals(HttpStatus.METHOD_NOT_ALLOWED,wex.getStatus());
            assertEquals(ERRORS_DAO_NOT_UPDATE_CODE,wex.getCode());
        }
    }


    @Test
    public void create_saveException() {
        //Given
        ShoppingListEntity entity = new ShoppingListEntity();
        given(shoppingListRepository.save(entity)).willThrow(new RuntimeException());

        //When
        try {
            warehouseShoppingListDao.create(entity);
        } catch (WarehouseException wex) {
            //Then
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,wex.getStatus());
            assertEquals(ERRORS_DAO_SAVE_SHOPPING_LIST_CODE,wex.getCode());
        }
    }


    @Test
    public void update() {
        //Given
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(1L);
        given(shoppingListRepository.findById(entity.getId())).willReturn(Optional.of(new ShoppingListEntity()));
        given(shoppingListRepository.save(entity)).willReturn(new ShoppingListEntity());

        //When
        ShoppingListEntity response = warehouseShoppingListDao.update(entity);

        //Then
        assertNotNull(response);
        verify(warehouseShoppingListDao,times(1)).getById(entity.getId());
        verify(shoppingListRepository,times(1)).save(entity);
    }

    @Test
    public void delete() {
        //Given
        Long id = 1L;
        given(shoppingListRepository.findById(id)).willReturn(Optional.of(new ShoppingListEntity()));
        ArgumentCaptor<ShoppingListEntity> shoppingListEntityArgumentCaptor = ArgumentCaptor.forClass(ShoppingListEntity.class);
        doNothing().when(shoppingListRepository).delete(shoppingListEntityArgumentCaptor.capture());

        //When
        warehouseShoppingListDao.delete(id);

        //Then
        verify(shoppingListRepository,times(1)).delete(shoppingListEntityArgumentCaptor.getValue());
    }
}