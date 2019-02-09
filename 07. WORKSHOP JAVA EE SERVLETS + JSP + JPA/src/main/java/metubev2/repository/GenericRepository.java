package metubev2.repository;

import java.util.List;

public interface GenericRepository<Entity, Id> {
    Entity save(Entity entity);

    List<Entity> findAll();

    Entity findById(Id id);

    long size();
}
