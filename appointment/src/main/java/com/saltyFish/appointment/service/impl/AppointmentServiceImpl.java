package com.saltyFish.appointment.service.impl;

import com.saltyFish.appointment.dto.AppointmentDto;
import com.saltyFish.appointment.entity.Appointment;
import com.saltyFish.appointment.exception.ResourceNotFoundException;
import com.saltyFish.appointment.mapper.AppointmentMapper;
import com.saltyFish.appointment.repository.AppointmentRepository;
import com.saltyFish.appointment.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private AppointmentRepository appointmentRepository;
    private final StreamBridge streamBridge;

    /**
     * @param appointmentDto - AppointmentDto Object
     */
    @Override
    public void bookAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = AppointmentMapper.mapToAppointment(appointmentDto, new Appointment());

    }

//    private void sendCommunication(Appointment appointment, Customer customer) {
//        var appointmentMsgDto = new com.eazybytes.accounts.dto.AppointmentMsgDto(appointment.getConfirmationId(), customer.getEmail(), customer.getMobileNumber());
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
        Appointment appointment = appointmentRepository.findByConfirmationId(confirmationId).orElseThrow(
                () -> new ResourceNotFoundException("Appointment", "confirmationId", confirmationId)
        );
        AppointmentDto appointmentDto = AppointmentMapper.mapToAppointmentDto(appointment, new AppointmentDto());
        return appointmentDto;
    }

    /**
     * @param appointmentDto - AppointmentDto Object
     * @return boolean indicating if the update of appointment details is successful or not
     */
    @Override
    public boolean updateAppointment(AppointmentDto appointmentDto) {
        boolean isUpdated = false;
        if(appointmentDto !=null ){
            Appointment appointment = appointmentRepository.findById(appointmentDto.getAppointmentNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", appointmentDto.getAppointmentNumber().toString())
            );
            AppointmentMapper.mapToAppointment(appointmentDto, appointment);
            appointment = appointmentRepository.save(appointment);

            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * @param confirmationId - Input confirmation Id
     * @return boolean indicating if the delete of appointment details is successful or not
     */
    @Override
    public boolean deleteAppointment(String confirmationId) {
        Appointment appointment = appointmentRepository.findByConfirmationId(confirmationId).orElseThrow(
                () -> new ResourceNotFoundException("Appointment", "confirmationId", confirmationId)
        );
        appointmentRepository.deleteByConfirmationId(appointment.getConfirmationId());
        return true;
    }

    @Override
    public boolean updateCommunicationStatus(Long appointmentId) {
        boolean isUpdated = false;
        if (appointmentId != null) {
            Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", appointmentId.toString())
            );
            appointment.setCommunicationSw(true);
            appointmentRepository.save(appointment);
            isUpdated = true;
        }

        return isUpdated;
    }


}
