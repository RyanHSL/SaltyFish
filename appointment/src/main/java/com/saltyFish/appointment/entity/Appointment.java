package com.saltyFish.appointment.entity;

import com.saltyFish.appointment.lookups.ServiceType;
import jakarta.persistence.*;

import java.awt.print.Book;
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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String confirmationNumber;

    private Boolean communicationSw;

    private Long customerId;

    private boolean isCompleted;

    private boolean isCancelled;

    private Long serviceId;

    @OneToMany
    @JoinColumn(name = "appointmentId", nullable = false)
    private List<BookingDetails> bookingDetails = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "bookingDetailsId", nullable = false)
    private BookingDetails booking;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentUpdate> appointmentUpdates = new ArrayList<>();

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FollowUpActivity> followUpActivities = new ArrayList<>();

    public Appointment(){};

    public Appointment(Long serviceOwnerId, LocalDateTime startTime, LocalDateTime endTime, String confirmationNumber, Boolean communicationSw, Long customerId, boolean isCompleted, boolean isCancelled, Long serviceId, List<BookingDetails> bookingDetails, BookingDetails booking, List<AppointmentUpdate> appointmentUpdates, List<FollowUpActivity> followUpActivities) {
        this.serviceOwnerId = serviceOwnerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmationNumber = confirmationNumber;
        this.communicationSw = communicationSw;
        this.customerId = customerId;
        this.isCompleted = isCompleted;
        this.isCancelled = isCancelled;
        this.serviceId = serviceId;
        this.bookingDetails = bookingDetails;
        this.booking = booking;
        this.appointmentUpdates = appointmentUpdates;
        this.followUpActivities = followUpActivities;
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

    public List<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetails> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public List<FollowUpActivity> getFollowUpActivities() {
        return followUpActivities;
    }

    public void setFollowUpActivities(List<FollowUpActivity> followUpActivities) {
        this.followUpActivities = followUpActivities;
    }
}
