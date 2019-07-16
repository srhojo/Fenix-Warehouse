package com.hojo.fenix.warehouse.domain.mappers;

import com.hojo.fenix.warehouse.utils.mappers.InnerMapper;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.ProductCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.ProductSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.FoodRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FoodMapper implements InnerMapper<FoodEntity, FoodRequest> {

    private final QuantityMapper quantityMapper;

    public FoodMapper(QuantityMapper quantityMapper) {
        this.quantityMapper = quantityMapper;
    }

    @Override
    public FoodEntity mapToInner(FoodRequest outer) {
        final FoodEntity inner = new FoodEntity();
        inner.setName(outer.getName());
        inner.setDescription(outer.getDescription());
        inner.setCategory(new ProductCategoryEntity());
        inner.getCategory().setName(outer.getCategoryName());
        if (outer.getSubCategoryName() != null) {
            inner.setSubCategory(new ProductSubCategoryEntity());
            inner.getSubCategory().setName(outer.getSubCategoryName());
        }
        quantityMapper.toInner(outer.getQuantity()).ifPresent(inner::setQuantity);
        inner.setLastUpdatedDate(LocalDateTime.now());
        inner.setExpirationDate(outer.getExpirationDate());
        return inner;

    }
}
