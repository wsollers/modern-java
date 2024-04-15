package dev.wsollers.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {


  @JsonProperty("street_address")
  private String streetAddress;

  @JsonProperty("city")
  private String city;

  @JsonProperty("state")
  private String state;

  @JsonProperty("zip_code")
  private String zipCode;
/*
}        "address": {
            "street_address": "190 Carrie Freeway Suite 415",
            "city": "Walterton",
            "state": "Montana",
            "zip_code": "28717"
*/
}
