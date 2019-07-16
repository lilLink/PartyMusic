package ua.lillink.dao.impl;

import org.springframework.stereotype.Repository;
import ua.lillink.dao.PersonDao;
import ua.lillink.model.Person;

@Repository
public class PersonDaoImpl extends AbstractDao<Person, Long> implements PersonDao {
}
