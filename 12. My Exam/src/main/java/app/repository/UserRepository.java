package app.repository;

import app.domain.entity.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByUsername(String username);
}
