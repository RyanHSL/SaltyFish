package com.saltyFish.appointment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Appointment extends BaseEntity {

    private Long serviceOwnerId;

    private Long bookerId;

    @Id
    private Long appointmentNumber;

    private String serviceType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String confirmationId;

    private Boolean communicationSw;

}
