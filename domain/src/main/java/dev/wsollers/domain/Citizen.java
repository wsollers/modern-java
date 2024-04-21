package dev.wsollers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="Citizen")
@Table(name="CITIZEN", schema="CITIZENS")
public class Citizen extends AuditableEntity {

  @Id
  @JsonProperty("id")
  @Column(name="ID", length=36, nullable=false, unique=true)
  private String id;

  @Column(name="FIRST_NAME", length=64, nullable=false, unique=false)
  @JsonProperty("first_name")
  private String firstName;

  @Column(name="LAST_NAME", length=64, nullable=false, unique=false)
  @JsonProperty("last_name")
  private String lastName;

  @Column(name="SSN", length=64, nullable=false, unique=false)
  @JsonProperty("ssn")
  private String ssn;

  @Column(name="AGE", length=64, nullable=false, unique=false)
  @JsonProperty("age")
  private int age;

  //@Enumerated(EnumType.STRING)
  @Transient
  @JsonProperty("gender")
  private Gender gender;

  //@Enumerated(EnumType.STRING)
  @Transient
  @JsonProperty("race")
  private Race race;

  //@Enumerated(EnumType.STRING)
  @Transient
  @JsonProperty("ethnicity")
  private Ethnicity ethnicity;

  @Transient
  @JsonProperty("phone_number")
  private String phoneNumber;

  @Transient
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
