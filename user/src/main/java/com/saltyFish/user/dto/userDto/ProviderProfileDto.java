package com.saltyFish.user.dto.userDto;

import com.saltyFish.user.client.Address;
import com.saltyFish.user.client.Review;
import com.saltyFish.user.external.Appointment;
import com.saltyFish.user.lookups.ServiceType;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProviderProfileDto extends RequesterProfileDto {

    private String email;
    private Set<Long> services = new HashSet<>();
    private Set<ServiceType> serviceTypes = new HashSet<>();
    private Double serviceRating;
    private List<Appointment> serviceHistory = new ArrayList<>();
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
