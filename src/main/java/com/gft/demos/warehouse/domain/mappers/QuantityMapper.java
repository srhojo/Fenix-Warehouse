package com.gft.demos.warehouse.domain.mappers;

import com.gft.demos.warehouse.domain.cdm.Quantity;
import com.gft.demos.warehouse.domain.entities.QuantityEmbeddableEntity;
import com.gft.demos.warehouse.utils.mappers.InnerMapper;
import org.springframework.stereotype.Component;

@Component
public class QuantityMapper implements InnerMapper<QuantityEmbeddableEntity, Quantity> {

    @Override
    public QuantityEmbeddableEntity mapToInner(final Quantity outer) {
        QuantityEmbeddableEntity inner = new QuantityEmbeddableEntity();
        inner.setUnities(outer.getUnities());
        inner.setValue(outer.getValue());
        return inner;
    }
}
