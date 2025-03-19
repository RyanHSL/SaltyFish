package com.saltyFish.appointment.controller;

import com.saltyFish.appointment.constants.AppConstants;
import com.saltyFish.appointment.constants.AppointmentConstants;
import com.saltyFish.appointment.dto.*;
import com.saltyFish.appointment.lookups.AppointmentStatus;
import com.saltyFish.appointment.service.AppointmentService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ryan Hershel
 */

@Tag(
        name = "CRUD REST APIs for Appointment in SaltyFish",
        description = "CRUD REST APIs in SaltyFish to CREATE, UPDATE, FETCH AND DELETE appointment details"
)
@RestController
@RequestMapping(path="/api/v1/appointment", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

//    @Autowired
//    private AppointmentContactInfoDto appointmentContactInfoDto;

    @Operation(
            summary = "Create Appointment REST API",
            description = "REST API to create new Appointment inside Salty Fish"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/book")
    public ResponseEntity<APIResponse> bookAppointment(@Valid @RequestBody BookingDetailsDto bookingDetailsDto) {
        AppointmentDto appointment = appointmentService.bookAppointment(bookingDetailsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new APIResponse(AppointmentConstants.STATUS_201, AppointmentConstants.MESSAGE_201, appointment));
    }

    @Operation(
            summary = "Fetch Appointment Details REST API",
            description = "REST API to fetch Appointment details based on a confirmation idr"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<AppointmentDto> fetchAppointmentDetails(@RequestParam(name = "confirmationId")
                                                               @Pattern(regexp="(^$|[0-9]{8})",message = "confirmation Id must be 8 digits")
                                                               String confirmationId) {
        AppointmentDto appointmentDto = appointmentService.fetchAppointment(confirmationId);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentDto);
    }

    @Operation(
            summary = "Get all appointments for the user",
            description = "REST API to get Appointment details based on the current user id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch/{userId}")
    public ResponseEntity<APIResponse> fetchAllUserAppointments(@PathVariable Long userId,
                                                                         @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                         @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                         @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY) String sortBy,
                                                                         @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder) {
        Page<AppointmentDto> appointments = appointmentService.fetchUserAppointments(userId, pageNumber, pageSize, sortBy, sortOrder);
        if (appointments == null || appointments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
//        Integer totalElement = appointmentService.countUserAppointments(userId);
//        Integer totalPage = totalElement / pageSize;
//        boolean isLastPage = (pageNumber + 1) >= totalPage;
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse(AppointmentConstants.STATUS_200, AppointmentConstants.MESSAGE_200, appointments.getContent(), appointments.getNumber(), appointments.getSize(), appointments.getTotalElements(), appointments.getTotalPages(), appointments.isLast()));
    }

    @Operation(
            summary = "Update Appointment Details REST API",
            description = "REST API to update Appointment details based on a confirmation id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateAppointmentDetails(@Valid @RequestBody AppointmentDto appointmentDto) {
        boolean isUpdated = appointmentService.updateAppointment(appointmentDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new APIResponse(AppointmentConstants.STATUS_200, AppointmentConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new APIResponse(AppointmentConstants.STATUS_417,AppointmentConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<APIResponse> deleteAppointmentDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{8})",message = "confirmation Id must be 8 digits")
                                                                String confirmationId) {
        boolean isDeleted = appointmentService.deleteAppointment(confirmationId);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new APIResponse(AppointmentConstants.STATUS_200, AppointmentConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new APIResponse(AppointmentConstants.STATUS_417, AppointmentConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into appointment microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @Retry(name = "getBuildInfo",fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        logger.debug("getBuildInfo() method Invoked");
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable) {
        logger.debug("getBuildInfoFallback() method Invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("0.9");
    }

    @Operation(
            summary = "Get Java version",
            description = "Get Java versions details that is installed into appointment microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @RateLimiter(name= "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Java 17");
    }

//    @Operation(
//            summary = "Get Contact Info",
//            description = "Contact Info details that can be reached out in case of any issues"
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "HTTP Status OK"
//            ),
//            @ApiResponse(
//                    responseCode = "500",
//                    description = "HTTP Status Internal Server Error",
//                    content = @Content(
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    }
//    )
//    @GetMapping("/contact-info")
//    public ResponseEntity<AppointmentContactInfoDto> getContactInfo() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(appointmentContactInfoDto);
//    }


}
