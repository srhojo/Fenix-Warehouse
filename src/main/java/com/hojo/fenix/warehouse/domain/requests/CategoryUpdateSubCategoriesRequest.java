package com.hojo.fenix.warehouse.domain.requests;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryUpdateSubCategoriesRequest {

    @NotNull
    @ApiModelProperty(name = "CategoryId", required = true, example = "CAT1")
    private String categoryId;

    @NotEmpty
    @ApiModelProperty(name = "Subcategories id", required = true, example = "[\"SUB-CAT1\",\"SUB-CAT2\"]")
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
