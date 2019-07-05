package com.gft.demos.warehouse.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ShoppingListRequest implements Serializable {

    private static final long serialVersionUID = 8130430054506642574L;

    @JsonIgnore
    @ApiModelProperty(name = "id", notes = "ShoppingList id, not required, only to update info.")
    private Long id;

    @NotEmpty
    @Size(max = 200)
    @ApiModelProperty(name = "name", notes = "ShoppingList name. Max size 200", required = true)
    private String name;

    @Size(max = 500)
    @ApiModelProperty(name = "description", notes = "ShoppingList description. Max size 500")
    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
