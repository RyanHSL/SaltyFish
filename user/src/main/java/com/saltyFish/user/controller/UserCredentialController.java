package com.saltyFish.user.controller;

import com.saltyFish.user.dto.APIResponse;
import com.saltyFish.user.dto.ErrorResponseDto;
import com.saltyFish.user.dto.LoginRequest;
import com.saltyFish.user.dto.LogoutRequest;
import com.saltyFish.user.dto.userDto.RegistrationRequest;
import com.saltyFish.user.dto.userDto.UserDto;
import com.saltyFish.user.service.UserService;
import com.saltyFish.user.service.impl.KeycloakUserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Ryan Hershel
 */

@Tag(
        name = "CRUD REST APIs for User Microservice in SaltyFish",
        description = "CRUD REST APIs in SaltyFish to CREATE, UPDATE, FETCH AND DELETE user credentials"
)
@RestController
@RequestMapping(value = "/api/v1/userCredential", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UserCredentialController {

    private static final Logger logger = LoggerFactory.getLogger(UserCredentialController.class);

    private UserService userService;

    private KeycloakUserService keycloakUserService;

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client-id}")
    private String keycloakClientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Autowired
    public UserCredentialController(UserService userService, KeycloakUserService keycloakUserService) {
        this.userService = userService;
        this.keycloakUserService = keycloakUserService;
    }

    @Operation(
            summary = "Register a SaltyFish user",
            description = "Register a SaltyFish user with basic privileges"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
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
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        String keycloakId = keycloakUserService.createUserInKeycloak(registrationRequest);
        UserDto user = userService.findUserBykeycloakId(keycloakId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDto("User registration failed", HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed", LocalDateTime.now()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse("201", "User registered successfully", user));
    }

    @Operation(
            summary = "SaltyFish user login",
            description = "SaltyFish user login with the credentials"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "user logging in successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", keycloakClientId);
        params.add("client_secret", clientSecret);
        params.add("username", loginRequest.getUsername());
        params.add("password", loginRequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String tokenUrl = keycloakServerUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("200", "User logged in successfully", response.getBody()));
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto("Unauthorized", HttpStatus.UNAUTHORIZED, "Unauthorized", LocalDateTime.now()));
        }
    }

    @Operation(
            summary = "SaltyFish user login",
            description = "SaltyFish user login with the credentials"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "user logging in successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRquest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", keycloakClientId);
        params.add("client_secret", clientSecret);
        params.add("refresh_token", logoutRquest.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String logoutUrl = keycloakServerUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/logout";

        try {
            restTemplate.postForEntity(logoutUrl, request, Map.class);
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("200", "User logged out successfully", null));
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("Bad request", HttpStatus.BAD_REQUEST, "Bad request", LocalDateTime.now()));
        }

    }


    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;


    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into user microservice"
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
            description = "Get Java versions details that is installed into user microservice"
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
