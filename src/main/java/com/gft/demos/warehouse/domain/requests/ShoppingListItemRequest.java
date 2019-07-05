package com.gft.demos.warehouse.domain.requests;

import com.gft.demos.warehouse.domain.cdm.Quantity;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ShoppingListItemRequest implements Serializable {

    private static final long serialVersionUID = -3833663360348929223L;

    @NotEmpty
    @ApiModelProperty(name = "productId", notes = "Product id. the product has to be created at data base previously.", required = true)
    private String productId;

    @NotNull
    @Valid
    @ApiModelProperty(name = "quantity", required = true)
    private Quantity quantity;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
