package metubev2.repository;

import metubev2.domain.entity.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
}
