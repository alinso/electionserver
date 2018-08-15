package com.voting.demo.Voting;

import com.voting.demo.Dao.CitizenDao;
import com.voting.demo.Dao.ElectionDao;
import com.voting.demo.Dao.PartyDao;
import com.voting.demo.Dao.VoteDao;
import com.voting.demo.Entity.Citizen;
import com.voting.demo.Entity.Election;
import com.voting.demo.Entity.Party;
import com.voting.demo.Entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class VotingManager {

  private final CitizenDao citizenDao;

  private final PartyDao partyDao;

  private final VoteDao voteDao;

  private final ElectionDao electionDao;

  @Autowired
  public VotingManager(CitizenDao citizenDao, PartyDao partyDao, VoteDao voteDao, ElectionDao electionDao) {
    this.citizenDao = citizenDao;
    this.partyDao = partyDao;
    this.voteDao = voteDao;
    this.electionDao = electionDao;
  }


  private Election createNewElection(String electionDateString) {
    Election election = new Election();
    try {
      Date electionDate = new SimpleDateFormat("dd/MM/yyyy").parse(electionDateString);
      election.setElectionDate(electionDate);
      electionDao.create(election);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return election;

  }

  public void processElection() {


    List<Citizen> allCitizens = citizenDao.findAll();
    List<Party> allParties = partyDao.findAll();

    Random random = new Random();
    Integer day = random.nextInt(20) + 1;
    Integer month = random.nextInt(11) + 1;
    Integer year = random.nextInt(20) + 1980;
    Election election = createNewElection(day + "/" + month + "/" + year);


    for (Citizen citizen : allCitizens) {

      int randomPartyIndex = random.nextInt(allParties.size());

      Vote vote = new Vote();
      vote.setCitizen(citizen);
      vote.setParty(allParties.get(randomPartyIndex));
      vote.setElection(election);
      voteDao.create(vote);


    }
  }

}
