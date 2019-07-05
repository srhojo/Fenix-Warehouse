package com.gft.demos.warehouse.domain.mappers;

import com.gft.demos.warehouse.domain.entities.ProductCategoryEntity;
import com.gft.demos.warehouse.domain.requests.CategoryRequest;
import com.gft.demos.warehouse.utils.mappers.InnerMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper implements InnerMapper<ProductCategoryEntity, CategoryRequest> {

    @Override
    public ProductCategoryEntity mapToInner(CategoryRequest outer) {
        ProductCategoryEntity inner = new ProductCategoryEntity();
        inner.setName(outer.getName());
        inner.setDescription(outer.getDescription());
        inner.setImage(outer.getImage());
        return inner;
    }
}
