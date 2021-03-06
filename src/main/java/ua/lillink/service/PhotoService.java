package ua.lillink.service;

import org.springframework.web.multipart.MultipartFile;
import ua.lillink.model.profile.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoService {

    Optional<Photo> findById(Long id);

    List<Photo> findAll();

    byte[] loadAvatar(Long id);

    Photo save(Photo photo);

    Photo uploadAvatar(MultipartFile file, Long userId);

    Photo update(Photo photo);

    void deleteById(Long id);
}
