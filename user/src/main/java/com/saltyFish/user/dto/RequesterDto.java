package com.saltyFish.user.dto;

import com.saltyFish.appointment.lookups.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Appointment",
        description = "Schema to hold Appointment information"
)
public class RequesterDto {

    @NotEmpty(message = "Requester Id can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "RequesterId must be 10 digits")
    @Schema(
            description = "Requester ID of Salty Fish app", example = "3454433243"
    )
    private Long requesterId;

    @NotEmpty(message = "Service Type can not be a null or empty")
    @Schema(
            description = "Service type of Salty Fish app", example = "Makeup"
    )
    private ServiceType serviceType;

    @NotEmpty(message = "Confirmation Id can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{8})",message = "AppointmentNumber must be 8 digits")
    @Schema(
            description = "Confirmation Id", example = "12345678"
    )
    private String confirmationNumber;

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

    public RequesterDto() {}

    public RequesterDto(Long appointmentId, ServiceType serviceType, String confirmationNumber, LocalDateTime startTime, LocalDateTime endTime) {
        this.appointmentId = appointmentId;
        this.serviceType = serviceType;
        this.confirmationNumber = confirmationNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public @NotEmpty(message = "AppointmentNumber can not be a null or empty") @Pattern(regexp = "(^$|[0-9]{10})", message = "AppointmentNumber must be 10 digits") Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(@NotEmpty(message = "AppointmentNumber can not be a null or empty") @Pattern(regexp = "(^$|[0-9]{10})", message = "AppointmentNumber must be 10 digits") Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public @NotEmpty(message = "Service Type can not be a null or empty") ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(@NotEmpty(message = "Service Type can not be a null or empty") ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public @NotEmpty(message = "Confirmation Id can not be a null or empty") @Pattern(regexp = "(^$|[0-9]{8})", message = "AppointmentNumber must be 8 digits") String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(@NotEmpty(message = "Confirmation Id can not be a null or empty") @Pattern(regexp = "(^$|[0-9]{8})", message = "AppointmentNumber must be 8 digits") String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public @NotEmpty(message = "Start Time can not be a null or empty") @FutureOrPresent(message = "Start Time can not be a past date time") LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(@NotEmpty(message = "Start Time can not be a null or empty") @FutureOrPresent(message = "Start Time can not be a past date time") LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public @NotEmpty(message = "End Time can not be a null or empty") @FutureOrPresent(message = "End Time can not be a past date time") LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(@NotEmpty(message = "End Time can not be a null or empty") @FutureOrPresent(message = "End Time can not be a past date time") LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
