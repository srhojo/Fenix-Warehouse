package com.hojo.fenix.warehouse.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class ShoppingListItemsRequest implements Serializable {

    private static final long serialVersionUID = 3897253592423104546L;

    @JsonIgnore
    @ApiModelProperty(name = "shoppingListId", notes = "ShoppingList id, not required, only to update info.")
    private Long shoppingListId;

    @NotNull
    @Valid
    @ApiModelProperty(name = "items", notes = "ShoppingList id, not required, only to update info.",required = true)
    private List<ShoppingListItemRequest> items;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public List<ShoppingListItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItemRequest> items) {
        this.items = items;
    }
}
