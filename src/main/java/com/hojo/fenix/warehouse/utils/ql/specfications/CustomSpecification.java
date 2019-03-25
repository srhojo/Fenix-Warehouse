package com.hojo.fenix.warehouse.utils.ql.specfications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author hojo
 *
 */
public class CustomSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = -594590569552959173L;

    private final SearchCriteria criteria;

    public CustomSpecification(final SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query,
            final CriteriaBuilder criteriaBuilder) {
        switch (criteria.getOperation()) {
        case EQUALITY:
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        case NEGATION:
            return criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue());
        case GREATER_THAN:
            return criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
        case LESS_THAN:
            return criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
        case LIKE:
            return criteriaBuilder.like(criteriaBuilder.upper(root.get(criteria.getKey())),
                    "%" + ((String) criteria.getValue()).toUpperCase() + "%");
        default:
            return null;
        }
    }

}
