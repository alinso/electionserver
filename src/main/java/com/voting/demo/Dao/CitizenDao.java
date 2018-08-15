package com.voting.demo.Dao;

import com.voting.demo.Dao.Generic.BaseDao;
import com.voting.demo.Entity.Citizen;
import org.springframework.stereotype.Repository;

@Repository
public class CitizenDao extends BaseDao<Citizen> {

  public CitizenDao() {
    setClazz(Citizen.class);
  }

}
