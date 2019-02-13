package metubev2.repository;

import metubev2.domain.entity.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Tube save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Tube> findAll() {
        this.entityManager.getTransaction().begin();
        List<Tube> allTubes = this.entityManager
                .createQuery("" +
                        "SELECT t FROM Tube AS t", Tube.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return allTubes;
    }

    @Override
    public Tube findById(String id) {
        return this.entityManager
                .createQuery("" +
                        "SELECT t FROM Tube AS t " +
                        "WHERE t.id = :id ", Tube.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public long size() {
        this.entityManager.getTransaction().begin();
        long size = this.entityManager
                .createQuery("" +
                        "SELECT count(t) " +
                        "FROM Tube AS t", long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }

    @Override
    public Tube update(Tube tube) {
        this.entityManager.merge(tube);
        return tube;
    }
}
