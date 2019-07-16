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
    private ProductCategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "sub_category_name")
    private ProductSubCategoryEntity subCategory;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public ProductCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryEntity category) {
        this.category = category;
    }

    public ProductSubCategoryEntity getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductSubCategoryEntity subCategory) {
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
