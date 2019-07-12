package ua.lillink.dao;

import ua.lillink.model.Role;

public interface RoleDao extends BaseDao<Role, Long> {

    Role findByType(String type);

}
