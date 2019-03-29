package com.hojo.fenix.warehouse.domain.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="warehouse_products")
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
public class ProductEntity {

    @Id
    @Column(name = "name")
    protected String name;

    @NotNull
    @Column(name = "updated_date")
    protected LocalDateTime lastUpdatedDate;

    @Embedded
    protected QuantityEmbeddableEntity quantity;

    @Column(name = "description")
    protected String description;

    @Column(name = "image")
    protected byte[] image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public QuantityEmbeddableEntity getQuantity() {
        return quantity;
    }

    public void setQuantity(QuantityEmbeddableEntity quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
