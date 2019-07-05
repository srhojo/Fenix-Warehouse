package com.gft.demos.warehouse.domain.mappers;

import com.gft.demos.warehouse.domain.cdm.Quantity;
import com.gft.demos.warehouse.domain.entities.QuantityEmbeddableEntity;
import com.gft.demos.warehouse.domain.entities.ShoppingListItemsEntity;
import com.gft.demos.warehouse.domain.requests.ShoppingListItemRequest;
import com.gft.demos.warehouse.domain.requests.ShoppingListItemsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListItemMapperTest {

    @InjectMocks
    private ShoppingListItemMapper shoppingListItemMapper;

    @Mock
    private QuantityMapper quantityMapper;

    @Test
    public void mapToInner() {
        //Given
        final ShoppingListItemsRequest outer = new ShoppingListItemsRequest();
        outer.setShoppingListId(123l);

        final ShoppingListItemRequest itemRequest1 = new ShoppingListItemRequest();
        itemRequest1.setProductId("product1");
        itemRequest1.setQuantity(new Quantity());

        final ShoppingListItemRequest itemRequest2 = new ShoppingListItemRequest();
        itemRequest2.setProductId("product2");
        itemRequest2.setQuantity(new Quantity());

        outer.setItems(Arrays.asList(itemRequest1, itemRequest2));

        given(quantityMapper.toInner(ArgumentMatchers.any(Quantity.class))).willReturn(Optional.of(new QuantityEmbeddableEntity()));

        //When
        HashMap<String, ShoppingListItemsEntity> result = (HashMap<String, ShoppingListItemsEntity>) shoppingListItemMapper.mapToInner(outer);


        //Then
        assertEquals(outer.getItems().size(), result.size());
    }
}