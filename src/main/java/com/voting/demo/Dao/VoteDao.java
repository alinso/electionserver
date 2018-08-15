package com.voting.demo.Dao;

import com.voting.demo.Dao.Generic.BaseDao;
import com.voting.demo.Entity.Vote;
import org.springframework.stereotype.Repository;

@Repository
public class VoteDao extends BaseDao<Vote> {

public VoteDao(){
        setClazz(Vote.class);
        }
}