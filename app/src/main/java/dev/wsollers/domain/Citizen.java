package dev.wsollers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="Citizen")
@Table(name="CITIZEN", schema="TODO")
public class Citizen {

  @Id
  @JsonProperty("id")
  @Column(name="ID", length=64, nullable=false, unique=true)
  private String id;

  @Column(name="FIRST_NAME", length=64, nullable=false, unique=false)
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
