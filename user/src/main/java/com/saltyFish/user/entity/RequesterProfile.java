package com.saltyFish.user.entity;

import com.saltyFish.user.external.Appointment;
import com.saltyFish.user.lookups.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "requesterProfile")
public class RequesterProfile extends BaseEntity {

    @Id
    private Long id;

    @NotEmpty(message = "First Name can not be a null or empty")
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

    public RequesterProfile() {
    }

    public RequesterProfile(Long id, String firstName, String lastName, LocalDateTime dateOfBirth, Gender gender, String last4SSN, String phoneNumber, Set<Long> addresses, List<Appointment> appointments, Double balance, Double rating, List<Long> reviews) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.last4SSN = last4SSN;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.appointments = appointments;
        this.balance = balance;
        this.rating = rating;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "First Name can not be a null or empty") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty(message = "First Name can not be a null or empty") String firstName) {
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
