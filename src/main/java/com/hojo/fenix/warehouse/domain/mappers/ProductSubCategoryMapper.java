package com.hojo.fenix.warehouse.domain.mappers;

import com.hojo.fenix.warehouse.domain.entities.ProductSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.SubCategoryRequest;
import com.hojo.fenix.warehouse.utils.mappers.InnerMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductSubCategoryMapper implements InnerMapper<ProductSubCategoryEntity, SubCategoryRequest> {


    @Override
    public ProductSubCategoryEntity mapToInner(SubCategoryRequest outer) {
        ProductSubCategoryEntity inner = new ProductSubCategoryEntity();
        inner.setName(outer.getName());
        inner.setDescription(outer.getDescription());
        inner.setImage(outer.getImage());
        return inner;
    }
}
