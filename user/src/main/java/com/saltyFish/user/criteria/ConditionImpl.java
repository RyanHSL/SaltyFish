package com.saltyFish.user.criteria;

import com.saltyFish.user.criteria.interfaces.Condition;
import com.saltyFish.user.entity.RequesterProfile;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.lookups.Conditionals;

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
    public double getScore(User user, RequesterProfile userProfile, String evaluationAttribute) {
        return 0.0;
    }


}
