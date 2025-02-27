package com.saltyFish.appointment.entity;

import com.saltyFish.appointment.lookups.ServiceType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseEntity {

    private Long serviceOwnerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private ServiceType serviceType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String confirmationNumber;

    private Boolean communicationSw;

    private Long customerId;

    @OneToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private BookingDetails booking;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentUpdate> appointmentUpdates = new ArrayList<>();

    public Appointment(){};

    public Appointment(Long serviceOwnerId, ServiceType serviceType, LocalDateTime startTime, LocalDateTime endTime, String confirmationNumber, Boolean communicationSw, Long customerId, BookingDetails booking, List<AppointmentUpdate> appointmentUpdates) {
        this.serviceOwnerId = serviceOwnerId;
        this.serviceType = serviceType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmationNumber = confirmationNumber;
        this.communicationSw = communicationSw;
        this.customerId = customerId;
        this.booking = booking;
        this.appointmentUpdates = appointmentUpdates;
    }

    public void addUpdate(AppointmentUpdate update) {
        appointmentUpdates.add(update);
        update.setAppointment(this);
    }

    public Long getServiceOwnerId() {
        return serviceOwnerId;
    }

    public void setServiceOwnerId(Long serviceOwnerId) {
        this.serviceOwnerId = serviceOwnerId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public Boolean getCommunicationSw() {
        return communicationSw;
    }

    public void setCommunicationSw(Boolean communicationSw) {
        this.communicationSw = communicationSw;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BookingDetails getBooking() {
        return booking;
    }

    public void setBooking(BookingDetails booking) {
        this.booking = booking;
    }

    public List<AppointmentUpdate> getAppointmentUpdates() {
        return appointmentUpdates;
    }

    public void setAppointmentUpdates(List<AppointmentUpdate> appointmentUpdates) {
        this.appointmentUpdates = appointmentUpdates;
    }
}
