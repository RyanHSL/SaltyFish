package com.saltyFish.user.dto.userDto;

import com.saltyFish.user.client.Review;
import com.saltyFish.user.external.Appointment;
import com.saltyFish.user.lookups.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RequesterProfileDto {

    private String firstName;
    @NotEmpty(message = "Last Name can not be a null or empty")
    private String lastName;
    private LocalDateTime dateOfBirth;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    private String last4SSN;
    private String phoneNumber;
    private Set<Long> addresses = new HashSet<>();
    private List<Appointment> appointments = new ArrayList<>();
    private Double balance;
    private Double rating;
    private List<Long> reviews = new ArrayList<>();
//    private PaymentMethod paymentMethod;
//    private List<Payment> paymentHistory = new ArrayList<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty(message = "Last Name can not be a null or empty") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty(message = "Last Name can not be a null or empty") String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLast4SSN() {
        return last4SSN;
    }

    public void setLast4SSN(String last4SSN) {
        this.last4SSN = last4SSN;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Long> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Long> addresses) {
        this.addresses = addresses;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Long> getReviews() {
        return reviews;
    }

    public void setReviews(List<Long> reviews) {
        this.reviews = reviews;
    }
}
