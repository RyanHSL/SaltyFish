package com.saltyFish.user.dto.userDto;

import com.saltyFish.user.entity.Provider;
import com.saltyFish.user.lookups.Role;

import java.time.LocalDateTime;
import java.util.Set;

public class ProviderDto extends UserDto {

    private Role role;
    private Double rating;

    public ProviderDto() {}

    public ProviderDto(Role role) {
        this.role = role;
    }

    public ProviderDto(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Long> addresses, Boolean isMember, Boolean isActive, LocalDateTime startDate, LocalDateTime expiryDate, Integer level) {
        super(username, password, email, firstName, lastName, phoneNumber, addresses, isMember, isActive, startDate, expiryDate, level);
    }

    public ProviderDto(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Long> addresses, Boolean isMember, Boolean isActive, LocalDateTime startDate, LocalDateTime expiryDate, Integer level, Role role, Double rating) {
        super(username, password, email, firstName, lastName, phoneNumber, addresses, isMember, isActive, startDate, expiryDate, level);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
