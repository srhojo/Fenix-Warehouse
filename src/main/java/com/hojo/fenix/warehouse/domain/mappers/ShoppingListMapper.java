package com.hojo.fenix.warehouse.domain.mappers;

import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListRequest;
import com.hojo.fenix.warehouse.utils.mappers.InnerMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ShoppingListMapper implements InnerMapper<ShoppingListEntity, ShoppingListRequest> {

    @Override
    public ShoppingListEntity mapToInner(ShoppingListRequest outer) {
        final ShoppingListEntity inner = new ShoppingListEntity();
        inner.setId(outer.getId());
        inner.setName(outer.getName());
        inner.setDescription(outer.getDescription());
        inner.setLastUpdatedDate(LocalDateTime.now());
        return inner;
    }
}
