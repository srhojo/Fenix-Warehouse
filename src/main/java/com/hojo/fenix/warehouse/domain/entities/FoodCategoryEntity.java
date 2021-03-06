package com.hojo.fenix.warehouse.domain.entities;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import javax.persistence.*;

/**
 * @author hojo
 *
 */
@Entity
@Table(name = "warehouse_food_categories")
public class FoodCategoryEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private byte[] image;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="category_name", referencedColumnName = "name")
    private List<FoodSubCategoryEntity> subcategories;

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

    public List<FoodSubCategoryEntity> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<FoodSubCategoryEntity> subcategories) {
        this.subcategories = subcategories;
    }
}
