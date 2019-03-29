package com.hojo.fenix.warehouse.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "WAREHOUSE_SHOPPING_LIST")
public class ShoppingListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime lastUpdatedDate;

    @OneToMany
    @JoinColumn(name = "shopping_list_id")
    @MapKey(name = "id.shoppingListId")
    private Map<String, ShoppingListItemsEntity> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Map<String, ShoppingListItemsEntity> getItems() {
        return items;
    }

    public void setItems(Map<String, ShoppingListItemsEntity> items) {
        this.items = items;
    }
}
