package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.resource.NotSupportedException;

import ch.zli.m223.model.Tag;

@ApplicationScoped
public class TagService implements IService<Tag> {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public Tag create(Tag tag) {
    entityManager.persist(tag);
    return tag;
  }

  public List<Tag> findAll() {
    var query = entityManager.createQuery("FROM Tag", Tag.class);
    return query.getResultList();
  }

  public Tag find(Long id) {
    return entityManager.find(Tag.class, id);
  }

  public void delete(Long id) {
    entityManager.remove(find(id));
  }

  @Transactional
  public Tag update(Long id, Tag tag) throws NotSupportedException {
    if (tag.getId() != id) {
      throw new NotSupportedException("Ids do not match");
    }
    return entityManager.merge(tag);
  }
}
