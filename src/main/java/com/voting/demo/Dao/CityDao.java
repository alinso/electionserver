package com.voting.demo.Dao;


import com.voting.demo.Dao.Generic.BaseDao;
import com.voting.demo.Entity.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityDao extends BaseDao<City> {

  public CityDao(){
    setClazz(City.class);
  }
}
