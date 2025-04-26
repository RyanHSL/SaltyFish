package com.saltyFish.user.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.saltyFish.user.criteria.interfaces.Condition;
import com.saltyFish.user.entity.ProviderProfile;
import com.saltyFish.user.entity.RequesterProfile;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.lookups.Conditionals;

public class NumberCondition implements Condition<Double> {

    private String numericalConditionName;
    private double value;
    private Conditionals operator;

    @JsonCreator
    public NumberCondition(@JsonProperty("numericalConditionName") String numericalConditionName,
                           @JsonProperty("value") double value, Conditionals operator,
                           @JsonProperty("operator") Conditionals operatior) {
        numericalConditionName = numericalConditionName;
        value = value;
        operatior = operatior;
    }

    public NumberCondition() {}

    @JsonProperty("numericalConditionName")
    public String getNumericalConditionName() {
        return this.numericalConditionName;
    }

    @JsonProperty("numericalConditionName")
    public void setNumericalConditionName(String numericalConditionName) {
        this.numericalConditionName = numericalConditionName;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Conditionals getOperation() {
        return this.operator;
    }

    @Override
    public void setOperation(Conditionals operator) {
        this.operator = operator;
    }

    @Override
    public boolean evaluate(Double value) {
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
            double conditionValueDouble = ((Number) value).doubleValue();
            double attributeValueDouble = ((Number) this.value).doubleValue();

            return switch (operator) {
                case GREATER_THAN -> conditionValueDouble > attributeValueDouble;
                case LESS_THAN -> conditionValueDouble < attributeValueDouble;
                case GREATER_OR_EQUAL -> conditionValueDouble >= attributeValueDouble;
                case LESS_OR_EQUAL -> conditionValueDouble <= attributeValueDouble;
                default -> false;
            };
        }
    }


    @Override
    public double getScore(User user, RequesterProfile userProfile, String evaluationAttribute) {
        Double attributeValue = null;

        switch (evaluationAttribute) {
            case "age" -> attributeValue = Double.valueOf(userProfile.getAge());
            case "level" -> attributeValue = Double.valueOf(user.getLevel());
            case "balance" -> attributeValue = userProfile.getBalance();
            case "serviceRating" -> attributeValue = userProfile instanceof ProviderProfile ? ((ProviderProfile) userProfile).getServiceRating() : null;
        }

        return evaluate(attributeValue) ? 1.0 : 0.0;
    }
}
