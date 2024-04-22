package learning.project3.service;
import java.util.List;

import learning.project3.dto.TaskDto;
import learning.project3.entity.Task;

public interface TaskService {
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id);
    TaskDto createTask(TaskDto taskRequest);
    TaskDto updateTask(Long id, TaskDto taskRequest);
    void deleteTask(Long id);
    TaskDto updateStatus(Long id, String status);
    List<TaskDto> findTasksByUserId(Long userid);
}
