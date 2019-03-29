package com.hojo.fenix.warehouse.domain.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class ShoppingListItemsEmbeddableIdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "shopping_list_id")
    private Long shoppingListId;

    @Column(name = "product_id")
    @Size(max = 500)
    private String productId;

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
