package com.saltyFish.appointment.service.impl;

import com.saltyFish.appointment.dto.AppointmentDto;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.exception.ResourceNotFoundException;
import com.saltyFish.appointment.mapper.AppointmentMapper;
import com.saltyFish.appointment.repository.AppointmentDAO;
import com.saltyFish.appointment.service.AppointmentService;
import jakarta.persistence.PersistenceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;

import java.rmi.NotBoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private AppointmentDAO appointmentDAO;
    private final StreamBridge streamBridge;

    /**
     * @param appointmentDto - AppointmentDto Object
     */
    @Override
    public void bookAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = AppointmentMapper.mapToAppointment(appointmentDto, new Appointment());
        try {
            List<Appointment> existingAppointments = appointmentDAO.findAllAppointmentWithInThisTime(appointmentDto.getStartTime(), appointment.getEndTime());
            if (existingAppointments.isEmpty()) {
                appointmentDAO.save(appointment);
            }
            else {
                log.warn("This appointment has conflicts within this time period {} {}", appointmentDto.getStartTime(), appointment.getEndTime());
            }
        }
        catch (RuntimeException e) {
            log.error("Error finding available appointments within the time period {} {}", appointmentDto.getStartTime(), appointment.getEndTime(), e);
        }
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
     * @param appointmentDto - AppointmentDto Object
     * @return boolean indicating if the update of appointment details is successful or not
     */
    @Override
    public boolean updateAppointment(AppointmentDto appointmentDto) {
        boolean isUpdated = false;
        if(appointmentDto != null ){
            try {
                Appointment appointment = appointmentDAO.findById(appointmentDto.getAppointmentNumber());
                AppointmentMapper.mapToAppointment(appointmentDto, appointment);
                appointmentDAO.save(appointment);

                isUpdated = true;
            }
            catch (RuntimeException e) {
                log.error("Error retrieving appointment by appointment id: {}", appointmentDto.getAppointmentNumber(), e);
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
            appointmentDAO.deleteByConfirmationNumber(appointment.getConfirmationNumber());
        }
        catch (RuntimeException e) {
            log.error("Cannot find appointment by confirmation number: {}", confirmationId, e);
        }
        return isDeleted;
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
