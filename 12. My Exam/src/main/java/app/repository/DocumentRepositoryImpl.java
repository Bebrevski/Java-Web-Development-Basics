package app.repository;

import app.domain.entity.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {

    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Document save(Document entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Document> findAll() {
        return this.entityManager
                .createQuery("" +
                        "SELECT d FROM Document AS d ", Document.class)
                .getResultList();
    }

    @Override
    public Document findById(String id) {
        try {
            return this.entityManager
                    .createQuery("" +
                            "SELECT d " +
                            "FROM Document  AS d " +
                            "WHERE d.id = :id", Document.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("" +
                        "SELECT count(d) FROM Document AS d ", Long.class)
                .getSingleResult();
    }

    @Override
    public void printDocumentById(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("" +
                        "DELETE FROM Document d WHERE d.id = :id ")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
