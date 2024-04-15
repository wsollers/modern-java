package dev.wsollers.domain;

public enum Gender {
  MALE ("MALE"),
  FEMALE ("FEMALE"),
  OTHER ("OTHER");

  public final String textValue;

  Gender(String textValue) {
    this.textValue = textValue;
  }
};
