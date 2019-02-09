package metubev2.repository;

import metubev2.domain.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private EntityManager entityManager;

    public UserRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metubev2")
                .createEntityManager();
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<User> findAll() {
        this.entityManager.getTransaction().begin();
        List<User> allUsers = this.entityManager
                .createQuery("" +
                        "SELECT u FROM users AS u", User.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return allUsers;
    }

    @Override
    public User findById(String id) {
        this.entityManager.getTransaction().begin();
        User user = this.entityManager
                .createQuery("" +
                        "SELECT u FROM users AS u " +
                        "WHERE u.id = :id ", User.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return user;
    }

    @Override
    public long size() {
        this.entityManager.getTransaction().begin();
        long size = this.entityManager
                .createQuery("" +
                        "SELECT count(u) " +
                        "FROM users AS u", long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return size;
    }
}
