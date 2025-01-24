package com.saltyFish.appointment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AppointmentDto {

    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    @Schema(
            description = "Appoint Number of Salty Fish app", example = "3454433243"
    )
    private Long appointmentNumber;

    @NotEmpty(message = "Service Type can not be a null or empty")
    @Schema(
            description = "Service type of Salty Fish app", example = "Makeup"
    )
    private String serviceType;

    @NotEmpty(message = "Confirmation Id can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{8})",message = "AccountNumber must be 8 digits")
    @Schema(
            description = "Confirmation Id", example = "12345678"
    )
    private String confirmationId;

    @NotEmpty(message = "Start Time can not be a null or empty")
    @Schema(
            description = "Start time of a Salty Fish appointment", example = "01-24-2025"
    )
    @FutureOrPresent(message = "Start Time can not be a past date time")
    private LocalDateTime startTime;

    @NotEmpty(message = "End Time can not be a null or empty")
    @Schema(
            description = "End time of a Salty Fish appointment", example = "01-24-2025"
    )
    @FutureOrPresent(message = "End Time can not be a past date time")
    private LocalDateTime endTime;
}
