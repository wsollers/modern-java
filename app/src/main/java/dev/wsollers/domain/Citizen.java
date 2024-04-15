package dev.wsollers.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Citizen {

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("ssn")
  private String ssn;

  @JsonProperty("age")
  private int age;

  @JsonProperty("gender")
  private Gender gender;

  @JsonProperty("race")
  private Race race;

  @JsonProperty("ethnicity")
  private Ethnicity ethnicity;

  @JsonProperty("phone_number")
  private String phoneNumber;

  @JsonProperty("address")
  private Address address;

/*
    {
        "first_name": "Kathleen",
        "last_name": "Briggs",
        "ssn": "120-73-8269",
        "age": 55,
        "gender": "Female",
        "race": "White",
        "ethnicity": "Not Hispanic or Latino",
        "phone_number": "516-489-8296",
        "address": {
            "street_address": "190 Carrie Freeway Suite 415",
            "city": "Walterton",
            "state": "Montana",
            "zip_code": "28717"
        }
    },
*/
}
