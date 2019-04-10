package com.hojo.fenix.warehouse.domain.mappers;

import com.hojo.fenix.warehouse.domain.cdm.Quantity;
import com.hojo.fenix.warehouse.domain.entities.QuantityEmbeddableEntity;
import com.hojo.fenix.warehouse.utils.mappers.InnerMapper;
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
