package com.saltyFish.appointment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saltyFish.appointment.lookups.ServiceType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "followUpActivity")
public class FollowUpActivity {

    private Long serviceOwnerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followUpActivityId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String confirmationNumber;

    private Boolean communicationSw;

    private Long customerId;

    private boolean isCompleted;

    private boolean isCancelled;

    private Long serviceId;

    @ManyToOne
    @JoinColumn(name = "appointmentId", nullable = false)
    @JsonIgnore
    private Appointment appointment;

    public FollowUpActivity(Long serviceOwnerId, Long followUpActivityId, LocalDateTime startTime, LocalDateTime endTime, String confirmationNumber, Boolean communicationSw, Long customerId, boolean isCompleted, boolean isCancelled, Long serviceId, Appointment appointment) {
        this.serviceOwnerId = serviceOwnerId;
        this.followUpActivityId = followUpActivityId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmationNumber = confirmationNumber;
        this.communicationSw = communicationSw;
        this.customerId = customerId;
        this.isCompleted = isCompleted;
        this.isCancelled = isCancelled;
        this.serviceId = serviceId;
        this.appointment = appointment;
    }

    public Long getServiceOwnerId() {
        return serviceOwnerId;
    }

    public void setServiceOwnerId(Long serviceOwnerId) {
        this.serviceOwnerId = serviceOwnerId;
    }

    public Long getFollowUpActivityId() {
        return followUpActivityId;
    }

    public void setFollowUpActivityId(Long followUpActivityId) {
        this.followUpActivityId = followUpActivityId;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
