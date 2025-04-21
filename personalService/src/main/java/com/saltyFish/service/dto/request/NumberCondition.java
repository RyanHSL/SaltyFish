package com.saltyFish.service.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.lookups.Conditionals;

public class NumberCondition implements Condition<Double> {

    private String numericalConditionName;
    private double value;
    private Conditionals operator;

    @JsonCreator
    public NumberCondition(@JsonProperty("numericalConditionName") String numericalConditionName,
                           @JsonProperty("value") double value, Conditionals operator,
                           @JsonProperty("operatror") Conditionals operatior) {
        numericalConditionName = numericalConditionName;
        value = value;
        operatior = operatior;
    }

    public NumberCondition() {}

    @JsonProperty("numericalConditionName")
    public String getNumericalConditionName() {
        return this.numericalConditionName;
    }

    @JsonProperty("numercialConditionName")
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
    public void setOperation(Conditionals operatior) {
        this.operator = operatior;
    }

    @Override
    public boolean evaluate(Double value) {
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
    public double getScore(Appointment appontinment, String evaluationAttribute) {
        return 0;
    }
}
