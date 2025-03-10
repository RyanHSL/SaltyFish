package com.saltyFish.user.entity;

import com.saltyFish.user.lookups.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
public class Provider extends User {

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    private List<Long> servicesHistory = new ArrayList<>();

    private List<Long> requestsHistory = new ArrayList<>();

    public Provider(String username, String password, String email) {
        super(username, password, email);
        this.role = Role.SERVICE_PROVIDER;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Long> getServicesHistory() {
        return servicesHistory;
    }

    public void setServicesHistory(List<Long> servicesHistory) {
        this.servicesHistory = servicesHistory;
    }

    public List<Long> getRequestsHistory() {
        return requestsHistory;
    }

    public void setRequestsHistory(List<Long> requestsHistory) {
        this.requestsHistory = requestsHistory;
    }
}
