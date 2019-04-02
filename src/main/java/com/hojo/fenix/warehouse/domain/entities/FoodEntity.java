package com.hojo.fenix.warehouse.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author hojo
 *
 */
@Entity
@Table(name = "warehouse_food_products")
public class FoodEntity extends ProductEntity {


    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCategoryEntity category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_sub_category_id")
    private FoodSubCategoryEntity foodSubCategoryEntity;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public FoodCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(FoodCategoryEntity category) {
        this.category = category;
    }

    public FoodSubCategoryEntity getFoodSubCategoryEntity() {
        return foodSubCategoryEntity;
    }

    public void setFoodSubCategoryEntity(FoodSubCategoryEntity foodSubCategoryEntity) {
        this.foodSubCategoryEntity = foodSubCategoryEntity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
