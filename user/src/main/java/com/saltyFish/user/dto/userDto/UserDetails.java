package com.saltyFish.user.dto.userDto;

import com.saltyFish.user.entity.User;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;

public class UserDetails {

    private String username;
    @Email
    private String email;
    private Boolean isMember;
    private Boolean isActive;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
    private Integer level;
    private RequesterProfileDto requesterProfile;
    private ProviderProfileDto providerProfile;

    public UserDetails() {}

    public UserDetails(String username, String email, Boolean isMember, Boolean isActive, LocalDateTime startDate, LocalDateTime expiryDate, Integer level, RequesterProfileDto requesterProfile, ProviderProfileDto providerProfile) {
        this.username = username;
        this.email = email;
        this.isMember = isMember;
        this.isActive = isActive;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.level = level;
        this.requesterProfile = requesterProfile;
        this.providerProfile = providerProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public Boolean getMember() {
        return isMember;
    }

    public void setMember(Boolean member) {
        isMember = member;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public RequesterProfileDto getRequesterProfile() {
        return requesterProfile;
    }

    public void setRequesterProfile(RequesterProfileDto requesterProfile) {
        this.requesterProfile = requesterProfile;
    }

    public ProviderProfileDto getProviderProfile() {
        return providerProfile;
    }

    public void setProviderProfile(ProviderProfileDto providerProfile) {
        this.providerProfile = providerProfile;
    }
}
