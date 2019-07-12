package ua.lillink.dao.impl;

import org.springframework.stereotype.Repository;
import ua.lillink.dao.AuthorDao;
import ua.lillink.model.Author;

@Repository
public class AuthorDaoImpl extends AbstractDao<Author, Long> implements AuthorDao {
}
