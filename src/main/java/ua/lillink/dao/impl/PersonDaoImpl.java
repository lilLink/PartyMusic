package ua.lillink.dao.impl;

import org.springframework.stereotype.Repository;
import ua.lillink.dao.PersonDao;
import ua.lillink.model.profile.Author;

@Repository
public class PersonDaoImpl extends AbstractDao<Author, Long> implements PersonDao {
}
