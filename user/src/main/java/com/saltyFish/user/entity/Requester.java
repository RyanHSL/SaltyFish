package com.saltyFish.user.entity;

import com.saltyFish.user.lookups.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "requester")
public class Requester extends User {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private Role role;

    private List<Long> requestsHistory = new ArrayList<>();
    private List<Long> servicesHistory = new ArrayList<>();

    public Requester(String username, String password, String email) {
        super(username, password, email);
        this.role = Role.REQUESTER;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Long> getRequestsHistory() {
        return requestsHistory;
    }

    public void setRequestsHistory(List<Long> requestsHistory) {
        this.requestsHistory = requestsHistory;
    }

    public List<Long> getServicesHistory() {
        return servicesHistory;
    }

    public void setServicesHistory(List<Long> servicesHistory) {
        this.servicesHistory = servicesHistory;
    }
}
