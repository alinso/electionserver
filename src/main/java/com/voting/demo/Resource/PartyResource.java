package com.voting.demo.Resource;


import com.voting.demo.Dao.PartyDao;
import com.voting.demo.Entity.Party;
import com.voting.demo.JsonMapper.ReflectionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("party")
public class PartyResource {

  private final PartyDao partyDao;

  private ReflectionMapper mapper = new ReflectionMapper();

  @Autowired
  public PartyResource(PartyDao partyDao) {
    this.partyDao = partyDao;
  }


  @PostMapping("create")
  public void addCitizen(@RequestBody String partyJson) {
    Party party = (Party) mapper.jsonToObject(partyJson, new Party());
    partyDao.create(party);

  }



  @GetMapping("all")
  public String allParties() {
    List<Party> partyList = partyDao.findAll();
    return mapper.objectToJson(partyList);
  }


  @PostMapping("delete")
  public void deleteParties(@RequestBody String idsJson) {
    Integer defaultId = -1;
    List<Integer> partyIdList = (List<Integer>) mapper.jsonToObject(idsJson, defaultId);

    for (Integer id : partyIdList) {
      partyDao.deleteById(id);
    }

  }


  @PostMapping("update")
  public void updateCitizenById(@RequestBody String citizenJson) {
    Party party = (Party) mapper.jsonToObject(citizenJson, new Party());
    partyDao.update(party);
  }


}
