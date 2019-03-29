package com.hojo.fenix.warehouse.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @author hojo
 */
@Entity
@Table(name = "warehouse_food_sub_categories")
public class FoodSubCategoryEntity {


    @Column(name = "category_id")
    private String categoryId;

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private byte[] image;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
