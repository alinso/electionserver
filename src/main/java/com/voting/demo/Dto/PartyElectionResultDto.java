package com.voting.demo.Dto;

public class PartyElectionResultDto {
  private Integer voteCount;
  private String name;
  private String election;


  public Integer getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(Integer voteCount) {
    this.voteCount = voteCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getElection() {
    return election;
  }

  public void setElection(String election) {
    this.election = election;
  }
}
