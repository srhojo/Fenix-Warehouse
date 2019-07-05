package com.gft.demos.warehouse.domain.mappers;

import com.gft.demos.warehouse.domain.entities.ProductSubCategoryEntity;
import com.gft.demos.warehouse.domain.requests.SubCategoryRequest;
import com.gft.demos.warehouse.utils.mappers.InnerMapper;
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
