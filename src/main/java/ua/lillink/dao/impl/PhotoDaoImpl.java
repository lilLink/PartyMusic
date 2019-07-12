package ua.lillink.dao.impl;

import org.springframework.stereotype.Repository;
import ua.lillink.dao.PhotoDao;
import ua.lillink.model.profile.Photo;

@Repository
public class PhotoDaoImpl extends AbstractDao<Photo, Long> implements PhotoDao {
}
