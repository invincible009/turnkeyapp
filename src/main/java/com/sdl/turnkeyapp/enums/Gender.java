package com.sdl.turnkeyapp.enums;

public enum Gender {
  MALE("male"),
  FEMALE("female");
  private String description;

  Gender(String description) {
    this.description = description;
  }
}
