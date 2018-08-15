package com.voting.demo.Dao.Generic;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class BaseDao< T extends Serializable>
        extends AbstractDao< T > implements IGenericDao< T > {


}