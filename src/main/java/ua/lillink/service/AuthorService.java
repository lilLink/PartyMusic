package ua.lillink.service;

import ua.lillink.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    Author save(Author author);

    Author update(Author author);

    void deleteById(Long id);
}
