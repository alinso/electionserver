package com.voting.demo.Resource;

import com.voting.demo.Dao.ElectionDao;
import com.voting.demo.Dao.PartyDao;
import com.voting.demo.Dto.PartyElectionResultDto;
import com.voting.demo.Entity.Election;
import com.voting.demo.JsonMapper.ReflectionMapper;
import com.voting.demo.Voting.VotingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@Deprecated
public class HomeResource {


    private final PartyDao partyDao;

    private final ElectionDao electionDao;

    @Autowired
    public HomeResource(PartyDao partyDao, ElectionDao electionDao, VotingManager votingManager) {
        this.partyDao = partyDao;
        this.electionDao = electionDao;
    }



   /* @GetMapping("election-results")
    public String getElectionsResult(){

        ReflectionMapper mapper  = new ReflectionMapper();
        SimpleDateFormat dateFormat  = new SimpleDateFormat("dd.MM.yyyy");

        List<PartyElectionResultDto> partyElectionResultDtos= new ArrayList<>();
        List<Election> electionList = electionDao.findAll();


        for(Election election:electionList){
            Map<String,Integer> partiesVotesByElection  = partyDao.getVoteCountByElection(election);


            for (Map.Entry entry : partiesVotesByElection.entrySet()){
                PartyElectionResultDto partyElectionResultDto = new PartyElectionResultDto();


                partyElectionResultDto.setName((String) entry.getKey());
                partyElectionResultDto.setElection("Election no: "+election.getId()+", Election Date: "+dateFormat.format(election.getElectionDate()));
                partyElectionResultDto.setVoteCount((Integer)entry.getValue());

                partyElectionResultDtos.add(partyElectionResultDto);
            }
        }

            return mapper.objectToJson(partyElectionResultDtos);


    }


*/
}
