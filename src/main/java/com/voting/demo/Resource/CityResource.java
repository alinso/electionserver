package com.voting.demo.Resource;

import com.voting.demo.Dao.CityDao;
import com.voting.demo.Entity.City;
import com.voting.demo.JsonMapper.ReflectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("city")
@RestController
public class CityResource {

  private ReflectionMapper mapper  = new ReflectionMapper();

  @Autowired
  private CityDao cityDao;

  @PostMapping("create")
  public void addCity(@RequestBody String citizenJson){
    City city  = (City)mapper.jsonToObject(citizenJson, new City());
    cityDao.create(city);

  }


  @GetMapping("all")
  private String getAll(){
    List<City> cities  = cityDao.findAll();
    String citiesJson  =mapper.objectToJson(cities);
    return  citiesJson;
  }



}
