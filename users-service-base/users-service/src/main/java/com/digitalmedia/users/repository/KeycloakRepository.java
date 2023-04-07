package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class KeycloakRepository implements IKeycloakRepository{
    @Autowired
    private Keycloak keycloakClient;
    @Value("${dh.keycloak.realm}")
    private String realm;

    @Override
    public User findByUsername(String username) {
        List<UserRepresentation> userRepresentation = keycloakClient.realm(realm)
                .users()
                .search(username);

        if (!userRepresentation.isEmpty()){
            UserRepresentation userResponseMap = userRepresentation
                    .stream()
                    .findFirst()
                    .orElse(new UserRepresentation());
            return toUser(userResponseMap);
        }
        return new User("fe8433bf", "prueba1", "prueba", "1", "prueba@prueba.com");
    }

    @Override
    public List<User> getAllUsersNoAdmin() {
        List<UserRepresentation> keycloakusersList = keycloakClient.realm(realm).users().list();

        List<UserRepresentation> usersNoAdmin = keycloakusersList.stream()
                .filter(ur -> {
                    List<GroupRepresentation> groupsUser = keycloakClient.realm(realm)
                            .users()
                            .get(ur.getId())
                            .groups();
                    return groupsUser.stream().noneMatch(grupo -> Objects.equals(grupo.getName(), "admin"));
                }).collect(Collectors.toList());

        return usersNoAdmin.stream().map(user -> toUser(user)).collect(Collectors.toList());
    }


    private User toUser(UserRepresentation userRepresentation) {
        return new User(userRepresentation.getId(), userRepresentation.getUsername(),
                userRepresentation.getEmail(), userRepresentation.getFirstName(), userRepresentation.getLastName());
    }
}

