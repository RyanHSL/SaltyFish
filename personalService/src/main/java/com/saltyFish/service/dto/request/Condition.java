package com.saltyFish.service.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.lookups.Conditionals;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NumberCondition.class, name = "number"),
        @JsonSubTypes.Type(value = StringCondition.class, name = "string")
})
public interface Condition<T> {
    Conditionals getOperation();
    void setOperation(Conditionals operatior);
    boolean evaluate(T value);
    double getScore(Appointment appontinment, String evaluationAttribute);
}
