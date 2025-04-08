package com.saltyFish.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saltyFish.user.dto.userDto.ProviderProfileDto;
import com.saltyFish.user.lookups.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@MappedSuperclass
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "Username can not be a null or empty")
    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @NotEmpty(message = "Password can not be a null or empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email can not be a null or empty")
    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "requesterId", referencedColumnName = "id")
    private RequesterProfile requesterProfile;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "providerId", referencedColumnName = "id")
    private ProviderProfile providerProfile;

    private Boolean isMember;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
    private Integer level;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public User() {}

    public User(Long userId, String username, String password, String email, Boolean isMember, LocalDateTime startDate, LocalDateTime expiryDate, Integer level) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isMember = isMember;
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

    public Boolean getMember() {
        return isMember;
    }

    public void setMember(Boolean member) {
        isMember = member;
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

    public RequesterProfile getRequesterProfile() {
        return requesterProfile;
    }

    public void setRequesterProfile(RequesterProfile requesterProfile) {
        this.requesterProfile = requesterProfile;
    }

    public ProviderProfile getProviderProfile() {
        return providerProfile;
    }

    public void setProviderProfile(ProviderProfile providerProfile) {
        this.providerProfile = providerProfile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
