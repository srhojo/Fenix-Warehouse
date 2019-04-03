package com.hojo.fenix.warehouse.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "warehouse_shopping_list")
public class ShoppingListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "updated_date", nullable = false)
    private LocalDateTime lastUpdatedDate;

    @NotEmpty
    @Size(max = 200)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "id.shoppingListId")
    private Map<String, ShoppingListItemsEntity> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "ShoppingListEntity{" +
                "id=" + id +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }
}
