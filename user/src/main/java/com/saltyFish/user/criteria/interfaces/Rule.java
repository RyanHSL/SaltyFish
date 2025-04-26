package com.saltyFish.user.criteria.interfaces;

import com.saltyFish.user.lookups.Attributes;
import com.saltyFish.user.entity.RequesterProfile;

import java.util.List;

public interface Rule<T> {

    double calculateScoreWithCondition(RequesterProfile userProfile);
    List<T> filterByAppointmentScores(Attributes filter);
}
