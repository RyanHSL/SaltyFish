package com.saltyFish.appointment.criteria;

import com.saltyFish.appointment.criteria.interfaces.Condition;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.lookups.Conditionals;

public class ConditionImpl<T> implements Condition<T> {


    @Override
    public Conditionals getOperation() {
        return null;
    }

    @Override
    public void setOperation(Conditionals operatior) {

    }

    @Override
    public boolean evaluate(T value) {
        return false;
    }

    @Override
    public double getScore(Appointment appontinment, String evaluationAttribute) {
        return 0;
    }
}
