package com.saltyFish.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "memberedRequester")
public class MemberedRequester extends Requester {

    private LocalDateTime startDate;

    private LocalDateTime expiryDate;

    private boolean isActive;

    private Integer level;

    public MemberedRequester(String username, String password, String email) {
        super(username, password, email);
    }

    public MemberedRequester(String username, String password, String email, LocalDateTime startDate, LocalDateTime expiryDate, boolean isActive, Integer level) {
        super(username, password, email);
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
        this.level = level;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
