package com.gft.demos.warehouse.domain.entities;

import javax.persistence.*;
import java.util.List;

/**
 * @author hojo
 */
@Entity
@Table(name = "warehouse_product_categories")
public class ProductCategoryEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private byte[] image;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_name", referencedColumnName = "name")
    private List<ProductSubCategoryEntity> subcategories;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<ProductSubCategoryEntity> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<ProductSubCategoryEntity> subcategories) {
        this.subcategories = subcategories;
    }
}
