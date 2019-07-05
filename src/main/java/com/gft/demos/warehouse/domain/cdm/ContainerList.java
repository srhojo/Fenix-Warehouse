package com.gft.demos.warehouse.domain.cdm;

import java.util.List;

/**
 * @author hojo
 */
public class ContainerList<T> {

    private List<T> values;
    private OffsetPagination pagination;

    public ContainerList() {
        // do nothing
    }

    public ContainerList(final List<T> values) {
        super();
        this.values = values;
    }

    public ContainerList(List<T> values, OffsetPagination pagination) {
        this.values = values;
        this.pagination = pagination;
    }

    /**
     * @return the values
     */
    public List<T> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(final List<T> values) {
        this.values = values;
    }

    public OffsetPagination getPagination() {
        return pagination;
    }

    public void setPagination(OffsetPagination pagination) {
        this.pagination = pagination;
    }
}
