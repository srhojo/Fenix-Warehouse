package com.hojo.fenix.warehouse.domain.entities;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Arrays;

/**
 * @author hojo
 */
@Entity
@Table(name = "warehouse_food_sub_categories")
public class FoodSubCategoryEntity extends ResourceSupport {


    @Column(name = "category_name")
    private String categoryName;

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private byte[] image;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    @Override
    public String toString() {
        return "FoodSubCategoryEntity{" +
                "categoryName='" + categoryName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
