package com.saltyFish.user.dto.userDto;

import com.saltyFish.user.lookups.Role;

import java.time.LocalDateTime;
import java.util.Set;

public class RequesterDto extends UserDto {

    private Role role;

    public RequesterDto() {}

    public RequesterDto(Role role) {
        this.role = role;
    }

    public RequesterDto(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Long> addresses, Boolean isMember, Boolean isActive, LocalDateTime startDate, LocalDateTime expiryDate, Integer level) {
        super(username, password, email, firstName, lastName, phoneNumber, addresses, isMember, isActive, startDate, expiryDate, level);
    }

    public RequesterDto(String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Long> addresses, Boolean isMember, Boolean isActive, LocalDateTime startDate, LocalDateTime expiryDate, Integer level, Role role) {
        super(username, password, email, firstName, lastName, phoneNumber, addresses, isMember, isActive, startDate, expiryDate, level);
        this.role = role;
    }
}
