package com.digitalmedia.users.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

  @Id
  private String id;
  private String username;
  private String avatar;
  private String firstName;
  private String lastName;
  private String email;

  public User(String id, String username, String firstName, String lastName, String email) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public User(String username) {
    this.username = username;
  }
}
