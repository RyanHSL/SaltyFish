package com.saltyFish.appointment.service;

import com.saltyFish.appointment.dto.AppointmentDto;
import com.saltyFish.appointment.dto.BookingDetailsDto;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.entity.BookingDetails;

import java.util.List;

public interface AppointmentService {

    /**
     *
     * @param bookingDetailsDto - Booking Details
     * @return Appointment indicating if the appointment has been confirmed
     */
    AppointmentDto bookAppointment(BookingDetailsDto bookingDetailsDto);

    /**
     *
     * @param userId - Input User ID
     * @return Appointment Details based on a given userId
     */
    List<AppointmentDto> fetchUserAppointments(Long userId, Integer pageNumber, Integer pageSize);

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
     * @return boolean indicating if the delete of Appointment details is successful or not
     */
    boolean deleteAppointment(String confirmationNumber);

    /**
     *
     * @param userId - Input user id
     * @return boolean indicating if the delete of Appointment details is successful or not
     */
    Integer countUserAppointments(Long userId);

    boolean updateCommunicationStatus(Long appointmentId);
}
