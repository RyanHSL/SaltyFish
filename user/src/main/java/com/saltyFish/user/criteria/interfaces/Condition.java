package com.saltyFish.user.criteria.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.saltyFish.user.criteria.BooleanCondition;
import com.saltyFish.user.criteria.NumberCondition;
import com.saltyFish.user.criteria.StringCondition;
import com.saltyFish.user.criteria.TimeCondition;
import com.saltyFish.user.entity.RequesterProfile;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.lookups.Conditionals;

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

    /**
     *
     * @param user
     * @param userProfile
     * @param evaluationAttribute
     * @return average score
     */
    double getScore(User user, RequesterProfile userProfile, String evaluationAttribute);
}
