package ua.lillink.dao;

import ua.lillink.model.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User, Long> {

    Optional<User> getUserWithRoles(String username);

    Optional<User> findByEmail(String email);
}
