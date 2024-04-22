package learning.project3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.project3.entity.Comment;
import learning.project3.entity.Post;
import learning.project3.entity.Task;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByUserId(Long userId);
	List<Comment> findByPostId(Long userId);
}
