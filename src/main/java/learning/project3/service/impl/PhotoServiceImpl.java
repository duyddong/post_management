package learning.project3.service.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import learning.project3.dto.PhotoDto;
import learning.project3.entity.Photo;
import learning.project3.entity.Post;
import learning.project3.repository.PhotoRepository;
import learning.project3.repository.PostRepository;
import learning.project3.service.PhotoService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PhotoDto> getAllPhotos() {
        List<Photo> photos = photoRepository.findAll();
        return photos.stream()
                .map(photo -> modelMapper.map(photo, PhotoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PhotoDto getPhotoById(Long id) {
        Photo photo = photoRepository.findById(id).orElse(null);
        return modelMapper.map(photo, PhotoDto.class);
    }

    @Override
    public PhotoDto createPhoto(PhotoDto photoRequest) {
        Photo photo = new Photo();
        Post post = postRepository.findById(photoRequest.getPostId()).orElse(null);
        photo.setUrl(photoRequest.getUrl());
        photo.setDescription(photoRequest.getDescription());
        photo.setCreatedAt(new Date());
        photo.setPost(post);
        Photo createdPhoto = photoRepository.save(photo);
        return modelMapper.map(createdPhoto, PhotoDto.class);
    }

    @Override
    public PhotoDto updatePhoto(Long id, PhotoDto photoRequest) {
        Photo photo = photoRepository.findById(id).orElse(null);
        if (photo != null) {
            photo.setUrl(photoRequest.getUrl());
            photo.setDescription(photoRequest.getDescription());
            Photo updatedPhoto = photoRepository.save(photo);
            return modelMapper.map(updatedPhoto, PhotoDto.class);
        }
        return null;
    }

    @Override
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

	@Override
	public List<PhotoDto> findPhotoByPostId(Long id) {
		List<Photo> photos = photoRepository.findByPostId(id);
        return photos.stream()
                .map(photo -> modelMapper.map(photo, PhotoDto.class))
                .collect(Collectors.toList());
	}
}
