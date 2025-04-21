package com.saltyFish.appointment.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.saltyFish.appointment.criteria.interfaces.Condition;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.lookups.Conditionals;

@JsonTypeName("string")
public class StringCondition implements Condition<String> {

    private String attributeName;
    private String value;
    private Conditionals operator;

    @JsonCreator
    public StringCondition(
            @JsonProperty("attributeName") String attributeName,
            @JsonProperty("value") String value,
            @JsonProperty("operator") Conditionals operator) {
        this.attributeName = attributeName;
        this.value = value;
        this.operator = operator;
    }

    public StringCondition() {}

    @JsonProperty("attributeName")
    public String getStringAttributeName() {
        return this.attributeName;
    }

    @JsonProperty("attributeName")
    public void setStringAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
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
    public boolean evaluate(String stringAttribute) {
        switch(this.operator) {
            case EQUALS:
                return stringAttribute.equals(this.value);
            case CONTAINS:
                return stringAttribute.contains(this.value);
            default:
                return false;
        }
    }

    @Override
    public double getScore(Appointment appointment, String stringAttribute) {
        return 0.0;
    }
}
