package metubev2.repository;

import metubev2.domain.entity.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {

    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metubev2")
                .createEntityManager();
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
                        "SELECT t FROM tubes AS t", Tube.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return allTubes;
    }

    @Override
    public Tube findById(String id) {
        this.entityManager.getTransaction().begin();
        Tube tubeEntity = this.entityManager
                .createQuery("" +
                        "SELECT t FROM tubes AS t " +
                        "WHERE t.id = :id ", Tube.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return tubeEntity;
    }

    @Override
    public long size() {
        this.entityManager.getTransaction().begin();
        long size = this.entityManager
                .createQuery("" +
                        "SELECT count(t) " +
                        "FROM tubes AS t", long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
