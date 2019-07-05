package com.gft.demos.warehouse.domain.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name="warehouse_products")
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
public class ProductEntity {

    @Id
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "updated_date")
    private LocalDateTime lastUpdatedDate;

    @Embedded
    private QuantityEmbeddableEntity quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private byte[] image;

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

    @Override
    public String toString() {
        return "ProductEntity{" +
                "name='" + name + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
