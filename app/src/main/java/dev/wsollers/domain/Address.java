package dev.wsollers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="Address")
@Table(name="ADDRESS", schema="CITIZENS")
public class Address {

  @Id 
  @Column(name="ID", length=36, nullable=false, unique=true)
  private String id;

  @Column(name="STREET_ADDRESS", length=64, nullable=true, unique=false)
  @JsonProperty("street_address")
  private String streetAddress;

  @Column(name="CITY", length=64, nullable=true, unique=false)
  @JsonProperty("city")
  private String city;

  @Column(name="STATE", length=64, nullable=true, unique=false)
  @JsonProperty("state")
  private String state;

  @Column(name="ZIP_CODE", length=64, nullable=true, unique=false)
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
