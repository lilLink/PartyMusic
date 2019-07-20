package ua.lillink.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lillink.dao.MusicDao;
import ua.lillink.model.Music;
import ua.lillink.service.MusicService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
    public Music save(Music music) {
        return musicDao.save(music);
    }

    @Override
    public Music update(Music music, Long id) {
        return musicDao.update(music, id);
    }

    @Override
    public void deleteById(Long id) {
        musicDao.deleteById(id);
    }
}
