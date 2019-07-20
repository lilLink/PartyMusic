package ua.lillink.service;

import ua.lillink.dto.UserDto;
import ua.lillink.exception.UserAlreadyExistException;
import ua.lillink.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    User createDto(UserDto userDto) throws UserAlreadyExistException;

    User save(User user);

    User update(User user, Long id);

    void deleteById(Long id);

    boolean emailExists(String email);
}
