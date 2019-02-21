package panda.repository;

import panda.domain.entity.Package;
import panda.domain.entity.Status;
import panda.domain.models.service.PackageServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository {

    private final EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Package save(Package entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Package> findAll() {
        return this.entityManager
                .createQuery("" +
                        "SELECT p FROM Package AS p ", Package.class)
                .getResultList();
    }

    @Override
    public Package findById(String id) {
        return this.entityManager
                .createQuery("" +
                        "SELECT p " +
                        "FROM Package  AS p " +
                        "WHERE p.id = :id", Package.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Long size() {
        return this.entityManager
                .createQuery("" +
                        "SELECT count(p) FROM Package AS p ", Long.class)
                .getSingleResult();
    }

    @Override
    public List<Package> findAllPackagesByStatus(Status status) {
        return this.entityManager.createQuery("" +
                "SELECT p From Package AS p " +
                "WHERE p.status = :status", Package.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public Package updatePackage(Package entity) {
        this.entityManager.getTransaction().begin();
        Package updated = this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();

        return updated;
    }
}
