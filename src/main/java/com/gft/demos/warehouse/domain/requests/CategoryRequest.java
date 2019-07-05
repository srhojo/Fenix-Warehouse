package com.gft.demos.warehouse.domain.requests;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryRequest implements Serializable {
    private static final long serialVersionUID = 7551733028829608124L;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private byte[] image;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
