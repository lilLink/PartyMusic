package ua.lillink.service;

import ua.lillink.model.Music;

import java.util.List;
import java.util.Optional;

public interface MusicService {

    Optional<Music> findById(Long id);

    List<Music> findAll();

    Music save(Music music);

    Music update(Music music, Long id);

    void deleteById(Long id);
}
