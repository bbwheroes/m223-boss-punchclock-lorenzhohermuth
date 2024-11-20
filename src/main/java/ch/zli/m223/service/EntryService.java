package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.resource.NotSupportedException;

import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService implements IService<Entry> {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public Entry create(Entry entry) {
    entityManager.persist(entry);
    return entry;
  }

  public List<Entry> findAll() {
    var query = entityManager.createQuery("FROM Entry", Entry.class);
    return query.getResultList();
  }

  public Entry find(Long id) {
    return entityManager.find(Entry.class, id);
  }

  public void delete(Long id) {
    entityManager.remove(find(id));
  }

  @Transactional
  public Entry update(Long id, Entry entry) throws NotSupportedException {
    if (entry.getId() != id) {
      throw new NotSupportedException("Ids do not match");
    }
    return entityManager.merge(entry);
  }
}
