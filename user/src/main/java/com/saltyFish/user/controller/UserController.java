package com.saltyFish.user.controller;

import com.saltyFish.user.constants.AppConstants;
import com.saltyFish.user.constants.UserConstants;
import com.saltyFish.user.dto.APIResponse;
import com.saltyFish.user.dto.ErrorResponseDto;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    private UserService providerService;

    private UserService requesterService;

    @Autowired
    public UserController(UserService userService, UserService providerService, UserService requesterService) {
        this.userService = userService;
        this.providerService = providerService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllUsers(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY) String sortBy,
                                                   @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER) String sortOrder) {
        Page<UserDto> users = userService.findAllUsers(pageNumber, pageSize, sortBy, sortOrder);
        if (users == null || users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(UserConstants.STATUS_200, UserConstants.MESSAGE_200, users.getContent(), users.getNumber(), users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast()));
    }

    @GetMapping("/provider/all")
    public ResponseEntity<APIResponse> getAllProviders(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                       @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                       @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                       @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER, required = false) String sortOrder) {
        Page<UserDto> providers = providerService.findAllUsers(pageNumber, pageSize, sortBy, sortOrder);
        if (providers == null || providers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(UserConstants.STATUS_200, UserConstants.MESSAGE_200, providers.getContent(), providers.getNumber(), providers.getSize(), providers.getTotalElements(), providers.getTotalPages(), providers.isLast()));
    }

    @GetMapping("/requester/all")
    public ResponseEntity<APIResponse> getAllRequesters(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                        @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                        @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                        @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER, required = false) String sortOrder) {
        Page<UserDto> requesters = requesterService.findAllUsers(pageNumber, pageSize, sortBy, sortOrder);
        if (requesters == null || requesters.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
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
