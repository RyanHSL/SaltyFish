package com.saltyFish.user.criteria;

import com.saltyFish.user.criteria.interfaces.Condition;

import java.util.List;

public class ConditionWrapper {

    private List<Condition<?>> conditions;

    public ConditionWrapper() {
    }

    public List<Condition<?>> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition<?>> conditions) {
        this.conditions = conditions;
    }
}
