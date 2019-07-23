package ua.lillink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lillink.model.Author;
import ua.lillink.service.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/author/{id}")
    public Optional<Author> getMusic(@PathVariable("id") long id) {
        return authorService.findById(id);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAll() {
        List<Author> people = authorService.findAll();
        return ResponseEntity.ok().body(people);
    }

    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Author author) {
        authorService.update(author, id);
        return ResponseEntity.ok().body("Author has been updated successfully.");
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        authorService.deleteById(id);
        return ResponseEntity.ok().body("Author has been deleted successfully.");
    }

    @PostMapping("/insertAuthor")
    public Author insert(@RequestBody Author author) {
        return authorService.save(author);
    }
}
