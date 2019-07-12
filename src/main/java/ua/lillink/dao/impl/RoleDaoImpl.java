package ua.lillink.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.lillink.dao.RoleDao;
import ua.lillink.model.Role;

@Repository
public class RoleDaoImpl extends AbstractDao<Role, Long> implements RoleDao {

    public Role findByType(String type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Role where type = :type").setParameter("type", type);
        return (Role) query.getSingleResult();
    }

}
