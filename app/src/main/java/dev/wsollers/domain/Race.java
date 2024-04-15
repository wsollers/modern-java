package dev.wsollers.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Race {

    Native("American Indian or Alaska Native"),
    ASIAN("Asian"),
    BLACK("Black or African American"),
    PACIFIC_ISLANDER("Native Hawaiian or Other Pacific Islander"),
    WHITE("White");


  public final String textValue;

  Race(String textValue) {
    this.textValue = textValue;
  }

  @JsonValue
  public String getRace() {
    return textValue;
  }
}
