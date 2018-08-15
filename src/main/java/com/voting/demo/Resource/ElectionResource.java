package com.voting.demo.Resource;

import com.voting.demo.Dao.ElectionDao;
import com.voting.demo.Dao.PartyDao;
import com.voting.demo.Dto.PartyElectionResultDto;
import com.voting.demo.Entity.Election;
import com.voting.demo.JsonMapper.ReflectionMapper;
import com.voting.demo.Voting.VotingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("election")
public class ElectionResource {

  final
  private ElectionDao electionDao;

  private final PartyDao partyDao;

  private final VotingManager votingManager;

 private ReflectionMapper mapper  = new ReflectionMapper();;

  @Autowired
  public ElectionResource(ElectionDao electionDao, PartyDao partyDao, VotingManager votingManager) {
    this.electionDao = electionDao;

    this.partyDao = partyDao;
    this.votingManager = votingManager;
  }


  @GetMapping("all")
  private String allElections(){
    List<Election> electionList  = electionDao.findAll();
   return mapper.objectToJson(electionList);

  }

  @GetMapping("refresh")
  public String welcome(){

    votingManager.processElection();


    return "refreshed";
  }

  @GetMapping("{id}")
  private String getById(@PathVariable("id") int id){

      Election election  =electionDao.getById(id);
      Map<String,Integer> partiesVotesByElection  = partyDao.getVoteCountByElection(election);
      List<PartyElectionResultDto> partyElectionResultDtos= new ArrayList<>();

    SimpleDateFormat dateFormat  = new SimpleDateFormat("dd.MM.yyyy");
      for (Map.Entry entry : partiesVotesByElection.entrySet()){
        PartyElectionResultDto partyElectionResultDto = new PartyElectionResultDto();


        partyElectionResultDto.setName((String) entry.getKey());
        partyElectionResultDto.setElection("Election no: "+election.getId()+", Election Date: "+dateFormat.format(election.getElectionDate()));
        partyElectionResultDto.setVoteCount((Integer)entry.getValue());

        partyElectionResultDtos.add(partyElectionResultDto);
      }

    return mapper.objectToJson(partyElectionResultDtos);
  }

}
