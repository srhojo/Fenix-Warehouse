package com.hojo.fenix.warehouse.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryUpdateSubCategoriesRequest {

    @NotNull
    private String categoryId;

    @NotEmpty
    private List<String> subcategoryIds;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getSubcategoryIds() {
        return subcategoryIds;
    }

    public void setSubcategoryIds(List<String> subcategoryIds) {
        this.subcategoryIds = subcategoryIds;
    }
}
