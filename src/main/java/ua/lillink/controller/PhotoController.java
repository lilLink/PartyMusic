package ua.lillink.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.lillink.model.profile.Photo;
import ua.lillink.service.PhotoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/photo")
@Api(value = "PhotoControllerAPI", produces = MediaType.IMAGE_JPEG_VALUE)
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(path = {"/avatars/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get photo by specific id")
    public byte[] loadAvatar(@PathVariable("id") Long id) {
        return photoService.loadAvatar(id);
    }

    @PostMapping(path = "/avatars/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Upload photo for person with specific id")
    public Photo uploadAvatar(@Valid @RequestParam("file") MultipartFile file, @PathVariable("user_id") Long userId) {
        return photoService.uploadAvatar(file, userId);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete photo with specific id")
    public void deleteById(@PathVariable("id") Long id) {
        photoService.deleteById(id);
    }

}
