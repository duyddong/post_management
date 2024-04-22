package learning.project3.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import learning.project3.dto.PhotoDto;
import learning.project3.service.PhotoService;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ResponseEntity<List<PhotoDto>> getAllPhotos() {
        List<PhotoDto> photos = photoService.getAllPhotos();
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> getPhotoById(@PathVariable Long id) {
        PhotoDto photo = photoService.getPhotoById(id);
        if (photo != null) {
            return ResponseEntity.ok(photo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PhotoDto> createPhoto(@RequestBody PhotoDto photoRequest) {
        PhotoDto createdPhoto = photoService.createPhoto(photoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPhoto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoDto> updatePhoto(@PathVariable Long id, @RequestBody PhotoDto photoRequest) {
        PhotoDto updatedPhoto = photoService.updatePhoto(id, photoRequest);
        if (updatedPhoto != null) {
            return ResponseEntity.ok(updatedPhoto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/post/{id}")
    public ResponseEntity<List<PhotoDto>> findPhotoByPostId(@PathVariable Long id) {
        List<PhotoDto> photos = photoService.findPhotoByPostId(id);
        return ResponseEntity.ok(photos);
    }
}
