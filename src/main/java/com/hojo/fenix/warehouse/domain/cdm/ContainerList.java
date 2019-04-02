package com.hojo.fenix.warehouse.domain.cdm;

import java.util.List;

/**
 * @author hojo
 */
public class ContainerList<T> {

    // Valores
    private List<T> values;

    // Paginación??

    public ContainerList() {
        // do nothing
    }

    public ContainerList(final List<T> values) {
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
