package com.voting.demo.Resource;


import com.voting.demo.Dao.CitizenDao;
import com.voting.demo.Entity.Citizen;
import com.voting.demo.JsonMapper.ReflectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "citizen")
public class CitizenResource {

    private final CitizenDao citizenDao;

    private ReflectionMapper mapper  = new ReflectionMapper();

    @Autowired
    public CitizenResource(CitizenDao citizenDao) {
        this.citizenDao = citizenDao;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void addCitizen(@RequestBody String citizenJson){
        Citizen citizen  = (Citizen)mapper.jsonToObject(citizenJson, new Citizen());
        citizenDao.create(citizen);

    }



    @GetMapping(value = "/all")
    public String allCitizens(){
       List<Citizen> citizenList  = citizenDao.findAll();
        return mapper.objectToJson(citizenList);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteCitizensa(@RequestBody String idsJson){
        Integer defaultId=-1;
        List<Integer> citizenIdList  = (List<Integer>)mapper.jsonToObject(idsJson, defaultId);

        for(Integer id : citizenIdList){
            citizenDao.deleteById(id);
        }

    }



    @RequestMapping(value="/update")
    public void updateCitizenById( @RequestBody String citizenJson){
     Citizen citizen  = (Citizen) mapper.jsonToObject(citizenJson,new Citizen());
        citizenDao.update(citizen);
    }



}
