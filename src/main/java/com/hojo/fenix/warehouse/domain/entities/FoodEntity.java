package com.hojo.fenix.warehouse.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.hojo.fenix.warehouse.domain.entities.enums.FoodUnitiesEnum;

/**
 * @author hojo
 *
 */
@Entity
@Table(name = "warehouse_foods")
public class FoodEntity {

    @Id
    @Column(name = "NAME")
    private String name;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_CATEGORY_ID")
    private SubCategoryEntity subCategoryEntity;

    @NotNull
    private Long amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FoodUnitiesEnum unities;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "UPDATED_DATE")
    private LocalDateTime lastUpdatedDate;

    @Column(name = "EXPIRATION_DATE")
    private LocalDateTime expirationDate;

    @Column(name = "image")
    private byte[] image;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public CategoryEntity getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(final CategoryEntity category) {
        this.category = category;
    }

    /**
     * @return the subCategoryEntity
     */
    public SubCategoryEntity getSubCategoryEntity() {
        return subCategoryEntity;
    }

    /**
     * @param subCategoryEntity the subCategoryEntity to set
     */
    public void setSubCategoryEntity(final SubCategoryEntity subCategoryEntity) {
        this.subCategoryEntity = subCategoryEntity;
    }

    /**
     * @return the amount
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    /**
     * @return the unities
     */
    public FoodUnitiesEnum getUnities() {
        return unities;
    }

    /**
     * @param unities the unities to set
     */
    public void setUnities(final FoodUnitiesEnum unities) {
        this.unities = unities;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the lastUpdatedDate
     */
    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * @param lastUpdatedDate the lastUpdatedDate to set
     */
    public void setLastUpdatedDate(final LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * @return the expirationDate
     */
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(final LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(final byte[] image) {
        this.image = image;
    }

}
