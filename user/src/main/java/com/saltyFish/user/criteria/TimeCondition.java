package com.saltyFish.user.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.saltyFish.user.criteria.interfaces.Condition;
import com.saltyFish.user.entity.RequesterProfile;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.lookups.Conditionals;

import java.time.LocalDateTime;

@JsonTypeName("dateTime")
public class TimeCondition implements Condition<LocalDateTime> {

    private String dateTimeConditionName;
    private LocalDateTime value;
    private Conditionals operator;

    @JsonCreator
    public TimeCondition(@JsonProperty("dateTimeConditionName") String dateTimeConditionName,
                         @JsonProperty("value") LocalDateTime value,
                         @JsonProperty("operator") Conditionals operator) {
        this.dateTimeConditionName = dateTimeConditionName;
        this.value = value;
        this.operator = operator;
    }

    public TimeCondition() {}

    @JsonProperty("dateTimeConditionName")
    public String getDateTimeConditionName() {
        return dateTimeConditionName;
    }

    @JsonProperty("dateTimeConditionName")
    public void setDateTimeConditionName(String dateTimeConditionName) {
        this.dateTimeConditionName = dateTimeConditionName;
    }

    public LocalDateTime getValue() {
        return value;
    }

    public void setValue(LocalDateTime value) {
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
    public boolean evaluate(LocalDateTime value) {
        if (operator == Conditionals.EQUALS) {
            return value.equals(this.value);
        }
        else if (operator == Conditionals.NOT_EQUALS) {
            return !value.equals(this.value);
        }
        else {
            return switch (operator) {
                case LESS_THAN -> value.isBefore(this.value);
                case LESS_OR_EQUAL -> value.isBefore(this.value) || value.equals(this.value);
                case GREATER_THAN -> value.isAfter(this.value);
                case GREATER_OR_EQUAL -> value.isAfter(this.value) || value.equals(this.value);
                default -> false;
            };
        }
    }


    @Override
    public double getScore(User user, RequesterProfile userProfile, String evaluationAttribute) {
        LocalDateTime attributeValue = null;
        switch (evaluationAttribute) {
            case "startDate" -> attributeValue = user.getStartDate();
            case "expiryDate" -> attributeValue = user.getExpiryDate();
        }
        return evaluate(attributeValue) ? 1.0 : 0.0;
    }
}
