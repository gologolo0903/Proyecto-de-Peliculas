package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;

import java.util.List;

public interface IKeycloakRepository {

    User findByUsername(String name);
    List<User> getAllUsersNoAdmin();

}
