package com.hojo.fenix.warehouse.domain.entities;

import com.hojo.fenix.warehouse.domain.entities.enums.UnitiesEnum;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QuantityEmbeddableEntity {

    @Column(name="quantity")
    private Float value;

    @Column(name="unities")
    private UnitiesEnum Unities;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public UnitiesEnum getUnities() {
        return Unities;
    }

    public void setUnities(UnitiesEnum unities) {
        Unities = unities;
    }
}
