package com.chrtsam.cards.api.model.external;

import com.chrtsam.cards.api.model.helper.Condition;
import java.util.List;

/**
 *
 * @author Chris
 */
public class SearchRequest {

    private List<Condition> conditions;

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

}
