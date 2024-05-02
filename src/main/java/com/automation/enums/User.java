package com.automation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** Test users for the app. */
@Getter
@AllArgsConstructor
public enum User {
  STANDARD("standard_user", "secret_sauce"),
  LOCKED("locked_out_user", "secret_sauce"),
  PROBLEM("problem_user", "secret_sauce"),
  PERFORMANCE("performance_glitch_user", "secret_sauce"),
  ERROR("error_user", "secret_sauce"),
  VISUAL("visual_user", "secret_sauce");

  private final String username;
  private final String password;
}
