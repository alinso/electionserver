package com.voting.demo.Dto;



public class PartyVoteDto {
    private String partyName;
    private long voteCount;

    public PartyVoteDto(String partyName, long voteCount) {
        this.partyName = partyName;
        this.voteCount = voteCount;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }


    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }
}