package sbojgb.repository;

import sbojgb.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {
    private final EntityManager entityManager;

    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JobApplication save(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<JobApplication> findAll() {
        return this.entityManager
                .createQuery("" +
                        "SELECT j FROM JobApplication AS j ", JobApplication.class)
                .getResultList();
    }

    @Override
    public JobApplication findById(String id) {
        return this.entityManager
                .createQuery("" +
                        "SELECT j " +
                        "FROM JobApplication  AS j " +
                        "WHERE j.id = :id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("" +
                        "SELECT count(j) FROM JobApplication AS j ", Long.class)
                .getSingleResult();
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("" +
                        "DELETE FROM JobApplication j WHERE j.id = :id ")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
