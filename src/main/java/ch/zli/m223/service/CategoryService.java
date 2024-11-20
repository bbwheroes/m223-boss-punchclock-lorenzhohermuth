package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.resource.NotSupportedException;

import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public Category createCategory(Category category) {
    entityManager.persist(category);
    return category;
  }

  public List<Category> findAll() {
    var query = entityManager.createQuery("FROM Category", Category.class);
    return query.getResultList();
  }

  public Category findCategory(Long id) {
    return entityManager.find(Category.class, id);
  }

  public void deleteCategory(Long id) {
    entityManager.remove(findCategory(id));
  }

  @Transactional
  public Category updateCategory(Long id, Category category) throws NotSupportedException {
    if (category.getId() != id) {
      throw new NotSupportedException("Ids do not match");
    }
    return entityManager.merge(category);
  }
}
