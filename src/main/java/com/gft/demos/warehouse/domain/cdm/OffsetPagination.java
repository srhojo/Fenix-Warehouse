package com.gft.demos.warehouse.domain.cdm;

/**
 * @author Hojo
 */
public class OffsetPagination {

    private final Integer limit;
    private final Long offset;
    private final Long totalElements;

    public OffsetPagination(Integer limit, Long offset, Long totalElements) {
        this.limit = limit;
        this.offset = offset;
        this.totalElements = totalElements;
    }

    public Integer getLimit() {
        return limit;
    }

    public Long getOffset() {
        return offset;
    }

    public Long getTotalElements() {
        return totalElements;
    }
}
