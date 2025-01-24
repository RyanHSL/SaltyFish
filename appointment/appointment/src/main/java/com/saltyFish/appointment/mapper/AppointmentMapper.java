package com.saltyFish.appointment.mapper;

import com.saltyFish.appointment.dto.AppointmentDto;
import com.saltyFish.appointment.entity.Appointment;

public class AppointmentMapper {

    public static AppointmentDto mapToAppointmentDto(Appointment appointment, AppointmentDto appointmentDto) {
        appointmentDto.setAppointmentNumber(appointment.getAppointmentNumber());
        appointmentDto.setServiceType(appointment.getServiceType());
        appointmentDto.setConfirmationId(appointment.getConfirmationId());
        appointmentDto.setServiceType(appointment.getServiceType());
        appointmentDto.setStartTime(appointment.getStartTime());
        appointmentDto.setEndTime(appointment.getEndTime());
        return appointmentDto;
    }

    public static Appointment mapToAppointment(AppointmentDto appointmentDto, Appointment appointment) {
        appointment.setAppointmentNumber(appointmentDto.getAppointmentNumber());
        appointment.setServiceType(appointmentDto.getServiceType());
        appointment.setConfirmationId(appointmentDto.getConfirmationId());
        appointment.setServiceType(appointmentDto.getServiceType());
        appointment.setStartTime(appointmentDto.getStartTime());
        appointment.setEndTime(appointmentDto.getEndTime());
        return appointment;
    }

}
