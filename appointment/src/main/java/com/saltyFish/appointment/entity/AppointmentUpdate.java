package com.saltyFish.appointment.entity;

import com.saltyFish.appointment.lookups.AppointmentStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "appointmentUpdate")
public class AppointmentUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "appointmentId", nullable = false)
    private Appointment appointment;

    public AppointmentUpdate(AppointmentStatus appointmentStatus, Appointment appointment) {
        this.appointmentStatus = appointmentStatus;
        this.appointment = appointment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
