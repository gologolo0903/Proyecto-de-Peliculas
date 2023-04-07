package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserRequest;
import com.digitalmedia.users.model.dto.UsersNoAdmin;
import com.digitalmedia.users.service.IKeycloakService;
import com.digitalmedia.users.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
  private final IUserService userService;
  private final IKeycloakService keycloak;

  public UserController(IUserService userService, IKeycloakService keycloak) {
    this.userService = userService;
    this.keycloak = keycloak;
  }
  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('GROUP_admin')")
  public List<UsersNoAdmin> getAllNoAdmin() {
    return keycloak.getAllUsersNoAdmin();
  }

  @GetMapping("/admin/{username}")
  @PreAuthorize("hasAuthority('GROUP_admin')")
  public User detailByUsername(@RequestParam String username) {
    return keycloak.findByUsername(username);
  }

  @GetMapping("/me")
  public User getUserExtra(Principal principal) {
    return userService.validateAndGetUser(principal.getName());
  }

  @PostMapping("/me")
  public User saveUserExtra(@RequestBody UserRequest updateUserRequest, Principal principal) {
    Optional<User> userOptional = userService.getUserExtra(principal.getName());
    User userExtra = userOptional.orElseGet(() -> new User(principal.getName()));
    return userService.saveUserExtra(userExtra);
  }
}
