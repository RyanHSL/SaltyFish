package com.saltyFish.appointment.criteria.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.saltyFish.appointment.criteria.BooleanCondition;
import com.saltyFish.appointment.criteria.NumberCondition;
import com.saltyFish.appointment.criteria.StringCondition;
import com.saltyFish.appointment.criteria.TimeCondition;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.lookups.Conditionals;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NumberCondition.class, name = "number"),
        @JsonSubTypes.Type(value = StringCondition.class, name = "string"),
        @JsonSubTypes.Type(value = BooleanCondition.class, name = "boolean"),
        @JsonSubTypes.Type(value = TimeCondition.class, name = "time")
})
public interface Condition<T> {
    Conditionals getOperation();
    void setOperation(Conditionals operatior);
    boolean evaluate(T value);
    double getScore(Appointment appontinment, String evaluationAttribute);
}
