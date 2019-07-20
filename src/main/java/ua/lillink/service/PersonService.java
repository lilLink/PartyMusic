package ua.lillink.service;

import ua.lillink.model.profile.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Optional<Person> findById(Long id);

    List<Person> findAll();

    Person save(Person person);

    Person update(Person person, Long id);

    void deleteById(Long id);
}
