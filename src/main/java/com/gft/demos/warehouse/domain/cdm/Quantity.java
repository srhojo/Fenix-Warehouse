package com.gft.demos.warehouse.domain.cdm;


import com.gft.demos.warehouse.domain.cdm.enums.UnitiesEnum;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Quantity implements Serializable {

    private static final long serialVersionUID = -1642155337787270868L;

    @NotNull
    @ApiModelProperty(name = "quantity", required = true, example = "1")
    private Float value;

    @NotNull
    @ApiModelProperty(name = "Unities", required = true, example = "KILOGRAMS")
    private UnitiesEnum Unities;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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
