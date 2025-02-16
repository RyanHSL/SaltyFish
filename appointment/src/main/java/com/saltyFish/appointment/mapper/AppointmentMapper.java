package com.saltyFish.appointment.mapper;

import com.saltyFish.appointment.dto.AppointmentDto;
import com.saltyFish.appointment.entity.Appointment;

public class AppointmentMapper {

    public static AppointmentDto mapToAppointmentDto(Appointment appointment, AppointmentDto appointmentDto) {
        appointmentDto.setAppointmentId(appointment.getAppointmentId());
        appointmentDto.setServiceType(appointment.getServiceType());
        appointmentDto.setConfirmationNumber(appointment.getConfirmationNumber());
        appointmentDto.setServiceType(appointment.getServiceType());
        appointmentDto.setStartTime(appointment.getStartTime());
        appointmentDto.setEndTime(appointment.getEndTime());
        return appointmentDto;
    }

    public static Appointment mapToAppointment(AppointmentDto appointmentDto, Appointment appointment) {
        appointment.setAppointmentId(appointmentDto.getAppointmentId());
        appointment.setServiceType(appointmentDto.getServiceType());
        appointment.setConfirmationNumber(appointmentDto.getConfirmationNumber());
        appointment.setServiceType(appointmentDto.getServiceType());
        appointment.setStartTime(appointmentDto.getStartTime());
        appointment.setEndTime(appointmentDto.getEndTime());
        return appointment;
    }

}
