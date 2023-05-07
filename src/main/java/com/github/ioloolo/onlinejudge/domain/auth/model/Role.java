package com.github.ioloolo.onlinejudge.domain.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "roles")
public class Role {

  @Id
  private final String id;

  private final Roles name;

  public enum Roles {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN
  }
}
