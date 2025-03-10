package com.saltyFish.appointment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saltyFish.appointment.lookups.ServiceType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "followUpActivity")
public class FollowUpActivity {

    private Long serviceOwnerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followUpActivityId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String confirmationNumber;

    private Boolean communicationSw;

    private Long customerId;

    private boolean isCompleted;

    private boolean isCancelled;

    private Long serviceId;

    @ManyToOne
    @JoinColumn(name = "appointmentId", nullable = false)
    @JsonIgnore
    private Appointment appointment;


}
