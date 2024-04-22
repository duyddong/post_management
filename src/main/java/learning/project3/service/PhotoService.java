package learning.project3.service;
import learning.project3.dto.PhotoDto;
import java.util.List;

public interface PhotoService {
    List<PhotoDto> getAllPhotos();
    PhotoDto getPhotoById(Long id);
    PhotoDto createPhoto(PhotoDto photoRequest);
    PhotoDto updatePhoto(Long id, PhotoDto photoRequest);
    void deletePhoto(Long id);
    List<PhotoDto> findPhotoByPostId(Long id);
}
