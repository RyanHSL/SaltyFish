package com.saltyFish.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saltyFish.user.dto.userDto.ProviderProfileDto;
import com.saltyFish.user.lookups.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

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

//    @JsonIgnore
//    @NotEmpty(message = "Password can not be a null or empty")
//    @Size(min = 6, message = "Password must be at least 6 characters long")
//    private String password;

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
    private String keycloakId;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public User() {}

    public User(String username, String email, Boolean isMember, LocalDateTime startDate, LocalDateTime expiryDate, Integer level, Role role, String keycloakId) {
        this.username = username;
        this.email = email;
        this.isMember = isMember;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.level = level;
        this.role = role;
        this.keycloakId = keycloakId;
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

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }
}
