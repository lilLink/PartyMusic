package ua.lillink.service;

import ua.lillink.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

    boolean emailExists(String email);
}
