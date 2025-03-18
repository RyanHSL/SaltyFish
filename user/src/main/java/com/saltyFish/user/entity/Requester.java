package com.saltyFish.user.entity;

import com.saltyFish.user.lookups.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "requester")
public class Requester extends User {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private Role role;

    public Requester() {}

    public Requester(Long userId, String username, String password, String email, String firstName, String lastName, String phoneNumber, Set<Long> addresses) {
        super(userId, username, password, email, firstName, lastName, phoneNumber, addresses, false, true, null, null, null);
        this.role = Role.REQUESTER;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
