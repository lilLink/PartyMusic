package ua.lillink.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lillink.dao.AuthorDao;
import ua.lillink.model.Author;
import ua.lillink.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorDao.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public Author save(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author update(Author author) {
        return authorDao.update(author);
    }

    @Override
    public void deleteById(Long id) {
        authorDao.deleteById(id);
    }
}
