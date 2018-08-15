package com.voting.demo.Entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
public class City implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String cityName;

  private PoliticalInclination politicalInclination;

  public enum PoliticalInclination{
    RADICAL_REPUBLICAN,
    MIDDLE_REPUBLICAN,
    MIDDLE,
    MIDDLE_DEMOCRAT,
    RADICAL_DEMOCRAT
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public PoliticalInclination getPoliticalInclination() {
    return politicalInclination;
  }

  public void setPoliticalInclination(PoliticalInclination politicalInclination) {
    this.politicalInclination = politicalInclination;
  }


}
