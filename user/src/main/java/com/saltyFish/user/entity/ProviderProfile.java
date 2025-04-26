package com.saltyFish.user.entity;

import com.saltyFish.user.external.Appointment;
import com.saltyFish.user.lookups.Gender;
import com.saltyFish.user.lookups.ServiceType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "providerProfiles")
@PrimaryKeyJoinColumn(name = "id")
public class ProviderProfile extends RequesterProfile {

    private Set<Long> services = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "providerServices",
            joinColumns = @JoinColumn(name = "providerId"),
            inverseJoinColumns = @JoinColumn(name = "serviceTypeId")
    )
    private Set<ServiceType> serviceTypes = new HashSet<>();

    private Double serviceRating;

    private List<Appointment> serviceHistory = new ArrayList<>();

    public ProviderProfile() {
    }

    public ProviderProfile(Set<Long> services, Set<ServiceType> serviceTypes, Double serviceRating, List<Appointment> serviceHistory) {
        this.services = services;
        this.serviceTypes = serviceTypes;
        this.serviceRating = serviceRating;
        this.serviceHistory = serviceHistory;
    }

    public ProviderProfile(Long id, String firstName, String lastName, LocalDateTime dateOfBirth, Gender gender, String last4SSN, String phoneNumber, Set<Long> addresses, List<Appointment> appointments, Double balance, Double rating, List<Long> reviews, Set<Long> services, Set<ServiceType> serviceTypes, Double serviceRating, List<Appointment> serviceHistory, Integer age) {
        super(id, firstName, lastName, dateOfBirth, gender, last4SSN, phoneNumber, addresses, appointments, balance, rating, reviews, age);
        this.services = services;
        this.serviceTypes = serviceTypes;
        this.serviceRating = serviceRating;
        this.serviceHistory = serviceHistory;
    }

    public Set<Long> getServices() {
        return services;
    }

    public void setServices(Set<Long> services) {
        this.services = services;
    }

    public Set<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(Set<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Double getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(Double serviceRating) {
        this.serviceRating = serviceRating;
    }

    public List<Appointment> getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(List<Appointment> serviceHistory) {
        this.serviceHistory = serviceHistory;
    }
}
