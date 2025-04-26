package com.saltyFish.user.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.saltyFish.user.criteria.interfaces.Condition;
import com.saltyFish.user.entity.RequesterProfile;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.lookups.Conditionals;

@JsonTypeName("boolean")
public class BooleanCondition implements Condition<Boolean> {

    private String flagName;
    private Boolean value;
    private Conditionals operator;

    @JsonCreator
    public BooleanCondition(@JsonProperty("booleanConditionName") String flagName,
                            @JsonProperty("value") Boolean value,
                            @JsonProperty("operator") Conditionals operator) {
        this.flagName = flagName;
        this.value = value;
        this.operator = operator;
    }

    public BooleanCondition() {}

    @JsonProperty("booleanConditionName")
    public String getBooleanConditionName() {
        return this.flagName;
    }

    @JsonProperty("booleanConditionName")
    public void setBooleanConditionName(String flagName) {
        this.flagName = flagName;
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public Conditionals getOperation() {
        return this.operator;
    }

    @Override
    public void setOperation(Conditionals operatior) {
        this.operator = operatior;
    }

    @Override
    public boolean evaluate(Boolean value) {
        if (value == null) {
            return false;
        }
        if (operator == Conditionals.EQUALS) {
            return value == this.value;
        }
        else if (operator == Conditionals.NOT_EQUALS) {
            return value != this.value;
        }
        else {
            return false;
        }
    }

    @Override
    public double getScore(User user, RequesterProfile userProfile, String evaluationAttribute) {
        Boolean flagValue = null;
        switch (evaluationAttribute) {
            case "isMember" -> flagValue = user.getMember();
        }
        return evaluate(flagValue) ? 1.0 : 0.0;
    }
}
