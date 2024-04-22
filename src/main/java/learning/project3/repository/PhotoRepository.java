package learning.project3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.project3.entity.Comment;
import learning.project3.entity.Photo;
import learning.project3.entity.Post;

public interface PhotoRepository  extends JpaRepository<Photo, Long> {
	List<Photo> findByPostId(Long userId);
}
