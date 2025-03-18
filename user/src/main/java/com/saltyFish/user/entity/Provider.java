package com.saltyFish.user.entity;

import com.saltyFish.user.lookups.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "member")
public class Provider extends User {

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    private Double rating;

    public Provider() {}

    public Provider(Long userId, String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Long> addresses) {
        super(userId, username, password, email, firstName, lastName, phoneNumber, addresses, false, true, null, null, null);
        this.role = Role.SERVICE_PROVIDER;
        this.rating = 0.0;
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
