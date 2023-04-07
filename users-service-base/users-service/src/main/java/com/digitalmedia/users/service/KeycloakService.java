package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UsersNoAdmin;
import com.digitalmedia.users.repository.IKeycloakRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakService implements IKeycloakService {

    private IKeycloakRepository keycloakRepository;

    public KeycloakService(IKeycloakRepository keycloakRepository) {
        this.keycloakRepository = keycloakRepository;
    }


    @Override
    public User findByUsername(String username) {
        return keycloakRepository.findByUsername(username);
    }


    @Override
    public List<UsersNoAdmin> getAllUsersNoAdmin() {
        List<User> usersNoAdmin =  keycloakRepository.getAllUsersNoAdmin();
        return usersNoAdmin.stream()
                .map(user -> toUserResponse(user))
                .collect(Collectors.toList());
    }



    private UsersNoAdmin toUserResponse(User user) {
        return new UsersNoAdmin(user.getUsername(), user.getEmail());
    }
}