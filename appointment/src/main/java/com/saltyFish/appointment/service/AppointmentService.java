package com.saltyFish.appointment.service;

import com.saltyFish.appointment.dto.AppointmentDto;

public interface AppointmentService {

    /**
     *
     * @param appointmentDto - AppointmentDto Object
     */
    void bookAppointment(AppointmentDto appointmentDto);

    /**
     *
     * @param confirmationNumber - Input confirmation Number
     * @return Appointment Details based on a given mobileNumber
     */
    AppointmentDto fetchAppointment(String confirmationNumber);

    /**
     *
     * @param appointmentDto - AppointmentDto Object
     * @return boolean indicating if the update of Appointment details is successful or not
     */
    boolean updateAppointment(AppointmentDto appointmentDto);

    /**
     *
     * @param confirmationNumber - Input confirmation Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAppointment(String confirmationNumber);

    boolean updateCommunicationStatus(Long appointmentId);
}
