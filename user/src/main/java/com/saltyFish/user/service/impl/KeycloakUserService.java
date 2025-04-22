package com.saltyFish.user.service.impl;

import com.saltyFish.user.dto.userDto.RegistrationRequest;
import com.saltyFish.user.entity.User;
import com.saltyFish.user.lookups.Role;
import com.saltyFish.user.repository.UserDAO;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KeycloakUserService {

    private final Keycloak keycloak;
    private final String realm;

    private UserDAO userDAO;

    @Autowired
    public KeycloakUserService(@Value("${keycloak.auth-server-url}") String serverUrl,
                               @Value("${keycloak.realm}") String realm,
                               @Value("${keycloak.client-id}") String clientId,
                               @Value("${keycloak.client-secret}") String clientSecret,
                               @Value("${keycloak.username}") String username,
                               @Value("${keycloak.password}") String password,
                               UserDAO userDAO) {
        this.realm = realm;
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .username(username)
                .password(password)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .build();
        this.userDAO = userDAO;
    }

    public String createUserInKeycloak(RegistrationRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setEnabled(true);

        Response response = keycloak.realm(realm)
                .users()
                .create(user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Keycloak user creation failed: " + response.getStatusInfo());
        }

        String userId = CreatedResponseUtil.getCreatedId(response);
        User saltyFishUser = new User(request.getUsername(), request.getEmail(), false, LocalDateTime.now(), null, 1, Role.REQUESTER);
        userDAO.save(saltyFishUser);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.getPassword());
        credential.setTemporary(false);

        keycloak.realm(realm)
                .users()
                .get(userId)
                .resetPassword(credential);

        response.close();
        return userId;
    }
}
