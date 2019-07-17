package ua.lillink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lillink.model.Music;
import ua.lillink.service.MusicService;

import java.util.List;
import java.util.Optional;

@RestController
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping(value = "/music/{id}")
    public Optional<Music> getMusic(@PathVariable("id") long id) {
        return musicService.findById(id);
    }

    @GetMapping("/musics")
    public ResponseEntity<List<Music>> getAll() {
        List<Music> people = musicService.findAll();
        return ResponseEntity.ok().body(people);
    }

    @PutMapping("/updateMusic/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Music music) {
        musicService.update(music, id);
        return ResponseEntity.ok().body("Music has been updated successfully.");
    }

    @DeleteMapping("/deleteMusic/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        musicService.deleteById(id);
        return ResponseEntity.ok().body("Music has been deleted successfully.");
    }

    @PostMapping("/insertMusic")
    public Music insert(@RequestBody Music music) {
        return musicService.save(music);
    }
}
