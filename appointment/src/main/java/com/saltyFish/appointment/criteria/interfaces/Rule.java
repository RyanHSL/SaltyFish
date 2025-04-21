package com.saltyFish.appointment.criteria.interfaces;

import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.lookups.Attributes;

import java.util.List;

public interface Rule<T> {

    double calculateScoreWithCondition(Appointment apointment);
    List<T> filterByAppointmentScores(Attributes filter);
}
