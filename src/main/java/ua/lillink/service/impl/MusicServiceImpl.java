package ua.lillink.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.lillink.dao.MusicDao;
import ua.lillink.model.Music;
import ua.lillink.service.MusicService;

import java.util.List;
import java.util.Optional;

public class MusicServiceImpl implements MusicService {

    private final MusicDao musicDao;

    @Autowired
    public MusicServiceImpl(MusicDao musicDao) {
        this.musicDao = musicDao;
    }

    @Override
    public Optional<Music> findById(Long id) {
        return musicDao.findById(id);
    }

    @Override
    public List<Music> findAll() {
        return musicDao.findAll();
    }

    @Override
    public Music save(Music role) {
        return musicDao.save(role);
    }

    @Override
    public Music update(Music role) {
        return musicDao.update(role);
    }

    @Override
    public void deleteById(Long id) {
        musicDao.deleteById(id);
    }
}
