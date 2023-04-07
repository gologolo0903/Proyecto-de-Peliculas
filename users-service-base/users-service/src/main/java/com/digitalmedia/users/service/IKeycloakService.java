package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UsersNoAdmin;

import java.util.List;

public interface IKeycloakService {

    User findByUsername(String username);
    List<UsersNoAdmin> getAllUsersNoAdmin();
}
