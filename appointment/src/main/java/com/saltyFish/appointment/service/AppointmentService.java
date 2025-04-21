package com.saltyFish.appointment.service;

import com.saltyFish.appointment.criteria.interfaces.Condition;
import com.saltyFish.appointment.dto.AppointmentDto;
import com.saltyFish.appointment.dto.BookingDetailsDto;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.entity.BookingDetails;
import org.springframework.data.domain.Page;

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
     * @return List of Appointment
     */
    List<AppointmentDto> fetchAllAppointments();

    /**
     *
     * @param pageNumber - Input Page Number
     * @param pageSize - Input Page Size
     * @param sortBy - Input Sort By
     * @param sortOrder - Input Sort Order
     * @return Page of Appointment
     */
    Page<AppointmentDto> fetchAllAppointments(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    /**
     *
     * @param userId - Input User ID
     * @return List of user appointments
     */
    List<AppointmentDto> fetchUserAppointments(Long userId);

    /**
     *
     * @param userId - Input User ID
     * @return Appointment Details based on a given userId
     */
    Page<AppointmentDto> fetchUserAppointments(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

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

    /**
     *
     * @param conditions - List of conditions
     * @param customerId - Input customer id
     * @param serviceOwnerId - Input service owner id
     * @param cutoffScore - Input cutoff score
     * @return List of appointments
     */
    List<AppointmentDto> scoreAndFilterAppointments(List<Condition<?>> conditions, Long customerId, Long serviceOwnerId, double cutoffScore);

    boolean updateCommunicationStatus(Long appointmentId);
}
