package com.chrtsam.cards.api.model.helper;

import com.chrtsam.cards.api.model.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Date;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Chris
 */
public class ConditionBuilder implements Specification<Task> {

    private Condition condition;

    public ConditionBuilder(Condition condition) {
        this.condition = condition;
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (condition.getOperator().equals(Condition.EQUALS)) {
            return builder.equal(root.get(condition.getKey()), condition.getValue());
        } else if (condition.getOperator().equals(Condition.GREATER_THAN)) {
            return builder.greaterThan(root.<Date>get(condition.getKey()), (Date) condition.getValue());
        } else if (condition.getOperator().equals(Condition.LESS_THAN)) {
            return builder.lessThan(root.<Date>get(condition.getKey()), (Date) condition.getValue());
        } else if (condition.getOperator().equals(Condition.EQUALS_IGNORE_CASE)) {
            return builder.like(root.<String>get(condition.getKey()), "%" + condition.getValue() + "%");
        }
        return null;
    }

}
