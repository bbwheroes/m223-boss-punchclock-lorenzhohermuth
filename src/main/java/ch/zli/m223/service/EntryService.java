package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.resource.NotSupportedException;

import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public Entry createEntry(Entry entry) {
    entityManager.persist(entry);
    return entry;
  }

  public List<Entry> findAll() {
    var query = entityManager.createQuery("FROM Entry", Entry.class);
    return query.getResultList();
  }

  public Entry findEntry(Long id) {
    return entityManager.find(Entry.class, id);
  }

  public void deleteEntry(Long id) {
    entityManager.remove(findEntry(id));
  }

  @Transactional
  public Entry updateEntry(Long id, Entry entry) throws NotSupportedException {
    if (entry.getId() != id) {
      throw new NotSupportedException("Ids do not match");
    }
    return entityManager.merge(entry);
  }
}
