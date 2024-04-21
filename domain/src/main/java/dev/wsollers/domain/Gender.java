package dev.wsollers.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
  MALE ("Male"),
  FEMALE ("Female"),
  OTHER ("Other");

  public final String textValue;

  Gender(String textValue) {
    this.textValue = textValue;
  }

  @JsonValue
  public String getGender() {
    return textValue;
  }
};
