package com.saltyFish.user.dto.userDto;

import com.saltyFish.user.client.Address;
import com.saltyFish.user.external.Appointment;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.ArrayList;
import java.util.List;

public abstract class UserProfile {

    private String gender;
    private String birthDate;
    private String last4SSN;
    private List<Appointment> appointments = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private Double balance;
//    private ContactPreference contactPreference;
//    private PaymentPreference paymentPreference;
//    private ServicePreference servicePreference;
//    private Subscription subscription;


    public UserProfile() {
    }

    public UserProfile(String gender, String birthDate, String last4SSN, List<Appointment> appointments, List<Address> addresses, Double balance) {
        this.gender = gender;
        this.birthDate = birthDate;
        this.last4SSN = last4SSN;
        this.appointments = appointments;
        this.addresses = addresses;
        this.balance = balance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getLast4SSN() {
        return last4SSN;
    }

    public void setLast4SSN(String last4SSN) {
        this.last4SSN = last4SSN;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
