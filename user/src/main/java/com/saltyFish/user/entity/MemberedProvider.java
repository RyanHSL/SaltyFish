package com.saltyFish.user.entity;

import com.saltyFish.user.lookups.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "memberedProvider")
public class MemberedProvider extends Provider {

    private LocalDateTime startDate;

    private LocalDateTime expiryDate;

    private boolean isActive;

    private Integer level;

    public MemberedProvider(Long userId, String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Long> addresses, Role role, LocalDateTime startDate, LocalDateTime expiryDate, boolean isActive, Integer level) {
        super(userId, username, password, email, firstName, lastName, phoneNumber, addresses, true, Role.MEMBERED_SERVICE_PROVIDER);
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
