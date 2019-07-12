package ua.lillink.dao.impl;

import org.springframework.stereotype.Repository;
import ua.lillink.dao.MusicDao;
import ua.lillink.model.Music;

@Repository
public class MusicDaoImpl extends AbstractDao<Music, Long> implements MusicDao {
}
