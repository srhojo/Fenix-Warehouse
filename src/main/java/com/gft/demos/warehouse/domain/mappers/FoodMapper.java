package com.gft.demos.warehouse.domain.mappers;

import com.gft.demos.warehouse.utils.mappers.InnerMapper;
import com.gft.demos.warehouse.domain.entities.FoodEntity;
import com.gft.demos.warehouse.domain.entities.ProductCategoryEntity;
import com.gft.demos.warehouse.domain.entities.ProductSubCategoryEntity;
import com.gft.demos.warehouse.domain.requests.FoodRequest;
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
