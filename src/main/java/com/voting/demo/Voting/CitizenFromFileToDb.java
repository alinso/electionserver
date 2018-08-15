package com.voting.demo.Voting;

import com.voting.demo.Dao.CitizenDao;
import com.voting.demo.Entity.Citizen;
import com.voting.demo.JsonMapper.ReflectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Deprecated
@Component
public class CitizenFromFileToDb extends AbstractFromFileToBb {

    @Autowired
    CitizenDao citizenDao;


    public  void save(){
        ReflectionMapper mapper  = new ReflectionMapper();
        List<String> jsonParties = readDataInString();

        Citizen citizen;
        for(String partyJson :jsonParties){
            citizen  =   (Citizen) mapper.jsonToObject(partyJson,new Citizen());
            citizenDao.create(citizen);
        }
    }
}
