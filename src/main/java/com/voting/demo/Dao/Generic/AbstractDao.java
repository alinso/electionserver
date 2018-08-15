package com.voting.demo.Dao.Generic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T extends Serializable> {

  private Class<T> clazz;

  @PersistenceContext
  protected EntityManager entityManager;

  protected void setClazz(Class<T> clazzToSet) {
    this.clazz = clazzToSet;
  }

  @Transactional
  public T getById(int id) {
    return entityManager.find(clazz, id);
  }

  @Transactional
  public List<T> findAll() {
    List<T> result = entityManager
            .createQuery("from " + clazz.getName())
            .getResultList();
    return result;
  }

  @Transactional
  public void create(T entity) {
    entityManager.persist(entity);
  }


  @Transactional
  public T update(T entity) {
    return entityManager.merge(entity);
  }

  @Transactional
  public void delete(T entity) {
    entityManager.remove(entity);
  }

  @Transactional
  public void deleteById(int entityId) {
    T entity = getById(entityId);
    delete(entity);
  }
}