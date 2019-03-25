package com.hojo.fenix.warehouse.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryUpdateSubCategoriesRequest {

    @NotNull
    private Long categoryId;

    @NotEmpty
    private List<Long> subcategoryIds;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getSubcategoryIds() {
        return subcategoryIds;
    }

    public void setSubcategoryIds(List<Long> subcategoryIds) {
        this.subcategoryIds = subcategoryIds;
    }
}
