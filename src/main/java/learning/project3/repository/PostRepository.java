package learning.project3.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.project3.entity.Post;
import learning.project3.entity.Task;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByUserId(Long userId);
}
