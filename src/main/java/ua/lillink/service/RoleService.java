package ua.lillink.service;

import ua.lillink.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(Long id);

    List<Role> findAll();

    Role save(Role role);

    Role findByType(String type);

    Role update(Role role, Long id);

    void deleteById(Long id);
}
