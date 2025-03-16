package com.saltyFish.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public abstract class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull @NotEmpty
    private String username;

    @NotNull @NotEmpty
    private String password;

    @Email
    @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty
    private String firstName;

    @NotNull @NotEmpty
    private String lastName;

    private String phoneNumber;

    private Set<Long> addresses = new HashSet<>();

    private Boolean isMember;

    @NotNull @NotEmpty
    private Boolean isActive;

    private LocalDateTime startDate;

    private LocalDateTime expiryDate;

    private Integer level;

    public User(Long userId, String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Long> addresses, Boolean isMember, Boolean isActive, LocalDateTime startDate, LocalDateTime expiryDate, Integer level) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.isMember = isMember;
        this.isActive = isActive;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.level = level;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
