package dev.wsollers.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Ethnicity {

    HISPANIC("Hispanic or Latino"),
    NOT_HISPANIC("Not Hispanic or Latino");

  public final String textValue;

  Ethnicity(String textValue) {
    this.textValue = textValue;
  }

  @JsonValue
  public String getEthnicity() {
    return textValue;
  }
}
