package com.saltyFish.appointment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saltyFish.appointment.lookups.ServiceType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookingDetails")
public class BookingDetails extends BaseEntity{

    private Long serviceOwnerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private ServiceType serviceType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String confirmationNumber;

    private Boolean communicationSw;

    private Long customerId;

    private boolean ownerConfirmed;

    private boolean requesterConfirmed;

    public BookingDetails(){}

    public BookingDetails(Long serviceOwnerId, ServiceType serviceType, LocalDateTime startTime, LocalDateTime endTime, String confirmationNumber, Boolean communicationSw, Long customerId) {
        this.serviceOwnerId = serviceOwnerId;
        this.serviceType = serviceType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmationNumber = confirmationNumber;
        this.communicationSw = communicationSw;
        this.customerId = customerId;
    }

    public Long getServiceOwnerId() {
        return serviceOwnerId;
    }

    public void setServiceOwnerId(Long serviceOwnerId) {
        this.serviceOwnerId = serviceOwnerId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public boolean isOwnerConfirmed() {
        return ownerConfirmed;
    }

    public void setOwnerConfirmed(boolean ownerConfirmed) {
        this.ownerConfirmed = ownerConfirmed;
    }

    public boolean isRequesterConfirmed() {
        return requesterConfirmed;
    }

    public void setRequesterConfirmed(boolean requesterConfirmed) {
        this.requesterConfirmed = requesterConfirmed;
    }

    public boolean isConfirmed() {
        return ownerConfirmed && requesterConfirmed;
    }
}
