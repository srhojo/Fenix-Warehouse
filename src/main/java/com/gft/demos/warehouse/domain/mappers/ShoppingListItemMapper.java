package com.gft.demos.warehouse.domain.mappers;

import com.gft.demos.warehouse.domain.entities.ShoppingListItemsEmbeddableIdEntity;
import com.gft.demos.warehouse.domain.entities.ShoppingListItemsEntity;
import com.gft.demos.warehouse.domain.requests.ShoppingListItemRequest;
import com.gft.demos.warehouse.domain.requests.ShoppingListItemsRequest;
import com.gft.demos.warehouse.utils.mappers.InnerMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ShoppingListItemMapper implements InnerMapper<Map<String, ShoppingListItemsEntity>, ShoppingListItemsRequest> {

    private final QuantityMapper quantityMapper;

    public ShoppingListItemMapper(QuantityMapper quantityMapper) {
        this.quantityMapper = quantityMapper;
    }

    @Override
    public Map<String, ShoppingListItemsEntity> mapToInner(ShoppingListItemsRequest outer) {
        return outer.getItems()
                .stream()
                .map(item -> mapShoppingListItem(item, outer.getShoppingListId()))
                .collect(Collectors.toMap(o -> o.getId().getProductId(), o -> o));
    }


    private ShoppingListItemsEntity mapShoppingListItem(ShoppingListItemRequest item, Long idShoppingList) {
        ShoppingListItemsEntity itemEntity = new ShoppingListItemsEntity();
        ShoppingListItemsEmbeddableIdEntity shoppingListItemsEmbeddableIdEntity = new ShoppingListItemsEmbeddableIdEntity();
        shoppingListItemsEmbeddableIdEntity.setProductId(item.getProductId());
        shoppingListItemsEmbeddableIdEntity.setShoppingListId(idShoppingList);
        itemEntity.setId(shoppingListItemsEmbeddableIdEntity);
        quantityMapper.toInner(item.getQuantity()).ifPresent(itemEntity::setQuantity);
        return itemEntity;
    }

}
