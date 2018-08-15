package com.voting.demo.Dao;

import com.voting.demo.Dao.Generic.BaseDao;
import com.voting.demo.Dto.PartyVoteDto;
import com.voting.demo.Entity.Election;
import com.voting.demo.Entity.Party;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PartyDao extends BaseDao<Party> {

   public PartyDao(){
       setClazz(Party.class);
   }




   public Map<String,Integer> getVoteCountByElection(Election election){

       Map<String,Integer> result  = new HashMap<>();



       String queryString = "select new com.voting.demo.Dto.PartyVoteDto(p.partyName as partyName , count(v) as voteCount)" +
               " from Party p" +
               " left join p.votes v" +
               " where v.election=:election"+
               "  group by p.partyName";

        Query q  =entityManager.createQuery(queryString).setParameter("election",election);


        List<PartyVoteDto> listResult =    q.getResultList();
        for(PartyVoteDto partyVoteDto : listResult) {
            result.put(partyVoteDto.getPartyName(), Integer.parseInt(String.valueOf(partyVoteDto.getVoteCount())));
        }

       return result;
   }
}
