package com.saltyFish.appointment.service.impl;

import com.saltyFish.appointment.dto.AppointmentDto;
import com.saltyFish.appointment.dto.BookingDetailsDto;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.entity.AppointmentUpdate;
import com.saltyFish.appointment.entity.BookingDetails;
import com.saltyFish.appointment.mapper.AppointmentMapper;
import com.saltyFish.appointment.lookups.AppointmentStatus;
import com.saltyFish.appointment.mapper.BookingDetailsMapper;
import com.saltyFish.appointment.repository.AppointmentDAO;
import com.saltyFish.appointment.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private AppointmentDAO appointmentDAO;
    private final StreamBridge streamBridge;

    /**
     * @param bookingDetailsDto - BookingDetailsDto Object
     */
    @Override
    public AppointmentDto bookAppointment(BookingDetailsDto bookingDetailsDto) {
        BookingDetails bookingDetails = BookingDetailsMapper.mapToBookingDetails(bookingDetailsDto, new BookingDetails());
        try {
            if (bookingDetails.isOwnerConfirmed() && bookingDetails.isRequesterConfirmed()) {
                Appointment appointment = createAppointment(bookingDetails);
                appointment.addUpdate(new AppointmentUpdate(AppointmentStatus.IN_PROGRESS, appointment));
                if (checkAppointmentConflicts(appointment)) {
                    appointmentDAO.save(appointment);
                }
                else {
                    log.warn("This appointment has conflicts within this time period {} {}", bookingDetails.getStartTime(), bookingDetails.getEndTime());
                }
                return AppointmentMapper.mapToAppointmentDto(appointment, new AppointmentDto());
            }
        }
        catch (RuntimeException e) {
            log.error("Error finding available appointments within the time period {} {}", bookingDetails.getStartTime(), bookingDetails.getEndTime(), e);
        }
        return null;
    }

//    private void sendCommunication(Appointment appointment, Customer customer) {
//        var appointmentMsgDto = AppointmentMsgDto(appointment.getConfirmationId(), customer.getEmail(), customer.getMobileNumber());
//        log.info("Sending Communication request for the details: {}", appointmentMsgDto);
//        var result = streamBridge.send("sendCommunication-out-0", appointmentMsgDto);
//        log.info("Is the Communication request successfully processed? : {}", result);
//    }

    /**
     * @param confirmationId - Input confirmation Id
     * @return appointment Details based on a given confirmationId
     */
    @Override
    public AppointmentDto fetchAppointment(String confirmationId) {
        try {
            Appointment appointment = appointmentDAO.findByConfirmationNumber(confirmationId);
            AppointmentDto appointmentDto = AppointmentMapper.mapToAppointmentDto(appointment, new AppointmentDto());
            return appointmentDto;
        }
        catch (RuntimeException e) {
            log.error("Error retrieving appointment by confirmation number: {}", confirmationId, e);
            return new AppointmentDto();
        }
    }

    /**
     * @param userId - Input user Id
     * @return appointment Details based on a given user id
     */
    @Override
    public List<AppointmentDto> fetchUserAppointments(Long userId, Integer pageNumber, Integer pageSize) {
        List<Appointment> appointmentPage = appointmentDAO.findByCustomerIdPageable(userId, pageNumber, pageSize);
        return appointmentPage.stream().
                map(appintment -> AppointmentMapper.mapToAppointmentDto(appintment, new AppointmentDto()))
                .collect(Collectors.toList());
    }

    /**
     * @param appointmentDto - AppointmentDto Object
     * @return boolean indicating if the update of appointment details is successful or not
     */
    @Override
    public boolean updateAppointment(AppointmentDto appointmentDto) {
        boolean isUpdated = false;
        if(appointmentDto != null ){
            try {
                Appointment appointment = appointmentDAO.findById(appointmentDto.getAppointmentId());
                AppointmentMapper.mapToAppointment(appointmentDto, appointment);
                appointmentDAO.save(appointment);

                isUpdated = true;
            }
            catch (RuntimeException e) {
                log.error("Error retrieving appointment by appointment id: {}", appointmentDto.getAppointmentId(), e);
            }
        }
        return  isUpdated;
    }

    /**
     * @param confirmationId - Input confirmation Id
     * @return boolean indicating if the delete of appointment details is successful or not
     */
    @Override
    public boolean deleteAppointment(String confirmationId) {
        boolean isDeleted = false;
        try {
            Appointment appointment = appointmentDAO.findByConfirmationNumber(confirmationId);
            BookingDetails bookingDetails = new BookingDetails(appointment.getServiceOwnerId(), appointment.getServiceType(), appointment.getStartTime(), appointment.getEndTime(),
                    appointment.getConfirmationNumber(), appointment.getCommunicationSw(), appointment.getCustomerId());
            isDeleted = deleteAppointment(BookingDetailsMapper.mapToBookingDetailsDto(bookingDetails, new BookingDetailsDto()));
        }
        catch (RuntimeException e) {
            log.error("Cannot find appointment by confirmation number: {}", confirmationId, e);
        }
        return isDeleted;
    }

    private boolean checkAppointmentConflicts(Appointment appointment) {
        try {
            List<Appointment> existingAppointments = appointmentDAO.findAllAppointmentWithInThisTime(appointment.getStartTime(), appointment.getEndTime());
            if (existingAppointments.isEmpty()) {
                return false;
            }
            else {
                log.warn("This appointment has conflicts within this time period {} {}", appointment.getStartTime(), appointment.getEndTime());
                return true;
            }
        }
        catch (RuntimeException e) {
            log.error("Error finding available appointments within the time period {} {}", appointment.getStartTime(), appointment.getEndTime(), e);
            return false;
        }
    }

    private Appointment createAppointment(BookingDetails bookingDetails) {
        Appointment appointment = new Appointment();
        appointment.setBooking(bookingDetails);
        appointment.setConfirmationNumber(bookingDetails.getConfirmationNumber());
        appointment.setCommunicationSw(bookingDetails.getCommunicationSw());
        appointment.setCustomerId(bookingDetails.getCustomerId());
        appointment.setServiceOwnerId(bookingDetails.getServiceOwnerId());
        appointment.setStartTime(bookingDetails.getStartTime());
        appointment.setEndTime(bookingDetails.getEndTime());
        appointment.setServiceType(bookingDetails.getServiceType());
        AppointmentUpdate appointmentUpdate = new AppointmentUpdate(AppointmentStatus.CREATED, appointment);
        appointment.addUpdate(appointmentUpdate);

        return appointment;
    }

    private boolean deleteAppointment(BookingDetailsDto bookingDetailsDto) {
        Appointment appointment = appointmentDAO.findByConfirmationNumber(bookingDetailsDto.getConfirmationNumber());
        if (bookingDetailsDto.isOwnerConfirmed() && bookingDetailsDto.isRequesterConfirmed()) {
            appointmentDAO.delete(appointment);
            appointment.addUpdate(new AppointmentUpdate(AppointmentStatus.CANCELLED, appointment));
            return true;
        }
        else if (LocalDateTime.now().isAfter(appointment.getStartTime().minus(24, ChronoUnit.HOURS))) {
            log.warn("Cannot cancel the appointment within 24 hrs before the start time {}, please contact the provider to manually cancel it.", appointment.getStartTime());
            appointment.addUpdate(new AppointmentUpdate(AppointmentStatus.PENDING_APPROVAL_FROM_PROVIDER, appointment));
            return false;
        }
        else if (LocalDateTime.now().isBefore(appointment.getStartTime().minus(24, ChronoUnit.HOURS))) {
            log.info("Cancelled the appointment with confirmation number {}", appointment.getConfirmationNumber());
            appointmentDAO.delete(appointment);
            appointment.addUpdate(new AppointmentUpdate(AppointmentStatus.CANCELLED, appointment));
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCommunicationStatus(Long appointmentId) {
        boolean isUpdated = false;
        if (appointmentId != null) {
            try {
                Appointment appointment = appointmentDAO.findById(appointmentId);
                appointment.setCommunicationSw(true);
                appointmentDAO.save(appointment);
                isUpdated = true;
            }
            catch (RuntimeException e) {
                log.error("Error updating communication status by appointment id: {}", appointmentId, e);
            }
        }

        return isUpdated;
    }


}
