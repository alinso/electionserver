package com.voting.demo.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Vote implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  private Party party;

  @ManyToOne(fetch = FetchType.EAGER)
  private Election election;

  @OneToOne(cascade = CascadeType.ALL)
  private Citizen citizen;


  public Party getParty() {
    return party;
  }

  public void setParty(Party party) {
    this.party = party;
  }

  public Citizen getCitizen() {
    return citizen;
  }

  public void setCitizen(Citizen citizen) {
    this.citizen = citizen;
  }

  public Election getElection() {
    return election;
  }

  public void setElection(Election election) {
    this.election = election;
  }
}
