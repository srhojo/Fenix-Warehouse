package com.hojo.fenix.warehouse.domain.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author hojo
 *
 */
@Entity
@Table(name = "warehouse_categories")
public class CategoryEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private byte[] image;

    @OneToMany
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private List<SubCategoryEntity> subcategories;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

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

    /**
     * @return the subcategories
     */
    public List<SubCategoryEntity> getSubcategories() {
        return subcategories;
    }

    /**
     * @param subcategories the subcategories to set
     */
    public void setSubcategories(final List<SubCategoryEntity> subcategories) {
        this.subcategories = subcategories;
    }

}
