package com.voting.demo.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Citizen implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private String citizenName;


  @Column
  private Integer citizenAge;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCitizenName() {
    return citizenName;
  }

  public void setCitizenName(String citizenName) {
    this.citizenName = citizenName;
  }


  public Integer getCitizenAge() {
    return citizenAge;
  }

  public void setCitizenAge(Integer citizenAge) {
    this.citizenAge = citizenAge;
  }
}


