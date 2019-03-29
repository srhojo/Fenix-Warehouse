package com.hojo.fenix.warehouse.domain;

import java.util.List;

/**
 * @author hojo
 */
public class ContainerEntities<T> {

    // Valores
    private List<T> values;

    // Paginación??

    public ContainerEntities() {
        // do nothing
    }

    public ContainerEntities(final List<T> values) {
        super();
        this.values = values;
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

}
