package learning.project3.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.project3.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByUserId(Long userId);
}