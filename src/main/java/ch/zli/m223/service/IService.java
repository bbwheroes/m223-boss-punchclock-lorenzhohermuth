package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.resource.NotSupportedException;
import javax.transaction.Transactional;

@ApplicationScoped
public interface IService<T> {

  @Transactional
  public T create(T element);

  public List<T> findAll();

  public T find(Long id);

  public void delete(Long id);

  @Transactional
  public T update(Long id, T element) throws NotSupportedException;

}
