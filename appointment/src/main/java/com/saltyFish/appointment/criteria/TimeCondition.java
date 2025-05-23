package com.saltyFish.appointment.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.saltyFish.appointment.criteria.interfaces.Condition;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.lookups.Conditionals;

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
        if (value == null) {
            return false;
        }
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
    public double getScore(Appointment appointment, String evaluationAttribute) {
        LocalDateTime attributeValue = null;
        switch (evaluationAttribute) {
            case "startTime" -> attributeValue = appointment.getStartTime();
            case "endTime" -> attributeValue = appointment.getEndTime();
            default -> attributeValue = null;
        }
        return evaluate(attributeValue) ? 1.0 : 0.0;
    }
}
