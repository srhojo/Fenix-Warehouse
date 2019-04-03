package com.hojo.fenix.warehouse.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author hojo
 */
@Entity
@Table(name = "warehouse_food_products")
public class FoodEntity extends ProductEntity {


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_name", nullable = false)
    private FoodCategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "sub_category_name")
    private FoodSubCategoryEntity subCategory;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public FoodCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(FoodCategoryEntity category) {
        this.category = category;
    }

    public FoodSubCategoryEntity getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(FoodSubCategoryEntity subCategory) {
        this.subCategory = subCategory;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "category=" + category +
                ", subCategory=" + subCategory +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
