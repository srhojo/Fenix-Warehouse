package com.hojo.fenix.warehouse.domain.entities;


import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="warehouse_shopping_list_items")
public class ShoppingListItemsEntity {

    @EmbeddedId
    private ShoppingListItemsEmbeddableIdEntity id;

    @Embedded
    private QuantityEmbeddableEntity quantity;

    public ShoppingListItemsEmbeddableIdEntity getId() {
        return id;
    }

    public void setId(ShoppingListItemsEmbeddableIdEntity id) {
        this.id = id;
    }

    public QuantityEmbeddableEntity getQuantity() {
        return quantity;
    }

    public void setQuantity(QuantityEmbeddableEntity quantity) {
        this.quantity = quantity;
    }
}
