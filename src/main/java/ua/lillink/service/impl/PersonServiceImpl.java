package ua.lillink.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lillink.dao.PersonDao;
import ua.lillink.dao.UserDao;
import ua.lillink.exception.ResourceNotFoundException;
import ua.lillink.model.User;
import ua.lillink.model.profile.Person;
import ua.lillink.service.PersonService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;
    private final UserDao userDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao, UserDao userDao) {
        this.personDao = personDao;
        this.userDao = userDao;
    }

    @Override
    public Optional<Person> findById(Long id) {
        /*if (getLoggedUser().isPresent()) {
            Long loggedUserId = getLoggedUser().get().getUserId();
            if (id.equals(loggedUserId)) {
                return personDao.findById(loggedUserId);
            }
        }

        throw new ResourceNotFoundException(String.format("Person with id %d was not found?!", id));*/
        return personDao.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Person save(Person person) {
        return personDao.save(person);
    }

    @Override
    public Person update(Person person) {
        /*if (getLoggedUser().isPresent()) {
            Long loggedUserId = getLoggedUser().get().getUserId();
            if (person.getUserId().equals(loggedUserId)) {
                User user = userDao.findById(loggedUserId)
                        .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %d was not found?!", loggedUserId)));
                person.setUser(user);
            }
        }*/

        return personDao.update(person);
    }

    @Override
    public void deleteById(Long id) {
        personDao.deleteById(id);
    }
}
