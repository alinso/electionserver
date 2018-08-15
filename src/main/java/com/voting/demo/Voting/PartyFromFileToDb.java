package com.voting.demo.Voting;

import com.voting.demo.Dao.PartyDao;
import com.voting.demo.Entity.Party;
import com.voting.demo.JsonMapper.ReflectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Deprecated
@Component
public class PartyFromFileToDb extends AbstractFromFileToBb {


    @Autowired
    PartyDao partyDao;

    public   void save(){
        ReflectionMapper mapper  = new ReflectionMapper();
        List<String> jsonParties = readDataInString();

        Party party;
        for(String partyJson :jsonParties){
            party  =   (Party) mapper.jsonToObject(partyJson,new Party());
            partyDao.create(party);
        }
    }
}
