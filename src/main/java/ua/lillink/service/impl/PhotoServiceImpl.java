package ua.lillink.service.impl;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.lillink.dao.PersonDao;
import ua.lillink.dao.PhotoDao;
import ua.lillink.exception.NotSupportedExtensionException;
import ua.lillink.exception.ResourceNotFoundException;
import ua.lillink.model.enumtype.Extension;
import ua.lillink.model.profile.Person;
import ua.lillink.model.profile.Photo;
import ua.lillink.service.PhotoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private static final String UPLOAD_DIRECTORY_FOR_AVATARS = System.getProperty("user.dir") + "\\uploads\\avatars";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PhotoDao photoDao;
    private final PersonDao personDao;

    @Autowired
    public PhotoServiceImpl(PhotoDao photoDao, PersonDao personDao) {
        this.photoDao = photoDao;
        this.personDao = personDao;
    }

    @Override
    public Optional<Photo> findById(Long id) {
        return photoDao.findById(id);
    }

    @Override
    public List<Photo> findAll() {
        return photoDao.findAll();
    }

    @Override
    public byte[] loadAvatar(Long id) {
        return loadPhoto(id, UPLOAD_DIRECTORY_FOR_AVATARS);
    }

    private byte[] loadPhoto(Long id, String uploadDirectory) {
        Photo photo = photoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Photo with id %d was not found?!", id)));

        Path file = Paths.get(uploadDirectory).resolve(photo.getName());

        try {
            return FileUtils.readFileToByteArray(file.toFile());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

        throw new RuntimeException("An error has occurred while loading photo?! Try again.");
    }

    @Override
    public Photo save(Photo photo) {
        return photoDao.save(photo);
    }

    @Override
    public Photo uploadAvatar(MultipartFile file, Long userId) {
        String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        if (extension.equals(Extension.JPG.getType()) || extension.equals(Extension.JPEG.getType()) || extension.equals(Extension.PNG.getType())) {
            try {
                Photo savedPhoto = uploadPhoto(file, Paths.get(UPLOAD_DIRECTORY_FOR_AVATARS));

                Person person = personDao.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException(String.format("Person with id %d was not found?!", userId)));

                Photo deletedPhoto = person.getPhoto();

                person.setPhoto(savedPhoto);
                personDao.update(person);

                deletePhoto(deletedPhoto, Paths.get(UPLOAD_DIRECTORY_FOR_AVATARS));

                return savedPhoto;
            } catch (IOException ex) {
                logger.error(ex.getMessage());

                throw new RuntimeException("An error has occurred while uploading photo?! Try again.");
            }
        }

        throw new NotSupportedExtensionException("An error has occurred while uploading photo?! Check please your file extension and try again.");
    }

    @Override
    public Photo update(Photo photo) {
        return photoDao.update(photo);
    }

    @Override
    public void deleteById(Long id) {
        photoDao.deleteById(id);
    }

    private Photo uploadPhoto(MultipartFile file, Path pathForUpload) throws IOException {
        if (!Files.exists(pathForUpload) || !Files.isDirectory(pathForUpload)) {
            Files.createDirectories(pathForUpload);
        }

        String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") - 1);
        UUID uuid = UUID.randomUUID();

        Path path = Paths.get(pathForUpload.toString(), uuid.toString() + extension);
        Files.write(path, file.getBytes());

        Photo photo = new Photo();
        photo.setName(uuid.toString() + extension);

        return photoDao.save(photo);
    }

    private void deletePhoto(Photo photo, Path path) throws IOException {
        long deletedPhotoId = -1;

        if (photo != null) {
            Files.delete(path.resolve(photo.getName()));

            deletedPhotoId = photo.getPhotoId();
        }

        if (deletedPhotoId != -1) {
            photoDao.deleteById(deletedPhotoId);
        }
    }
}
