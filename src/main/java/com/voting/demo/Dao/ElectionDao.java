package com.voting.demo.Dao;

import com.voting.demo.Dao.Generic.BaseDao;
import com.voting.demo.Entity.Election;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ElectionDao extends BaseDao<Election> {

  public ElectionDao() {
    setClazz(Election.class);
  }
  
}
