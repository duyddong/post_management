package learning.project3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.project3.dto.TaskDto;
import learning.project3.entity.Task;
import learning.project3.entity.User;
import learning.project3.repository.TaskRepository;
import learning.project3.repository.UserRepository;
import learning.project3.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                    .map(task -> modelMapper.map(task, TaskDto.class))
                    .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id) {
    	Task task = taskRepository.findById(id).orElse(null);
    	TaskDto taskDto = modelMapper.map(task, TaskDto.class);
        return taskDto;
    }

    @Override
    public TaskDto createTask(TaskDto taskRequest) {
        Task task = new Task();
    	User user = userRepository.findById(taskRequest.getUserId()).orElse(null);
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDeadline(taskRequest.getDeadline());
        task.setStatus(taskRequest.getStatus());
        task.setUser(user);
        Task taskCreated =  taskRepository.save(task);
        TaskDto taskDto = modelMapper.map(taskCreated, TaskDto.class);
        return taskDto ;
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskRequest) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            task.setDeadline(taskRequest.getDeadline());
            task.setStatus(taskRequest.getStatus());
            Task taskupdated =	taskRepository.save(task);
            TaskDto taskDto = modelMapper.map(taskupdated, TaskDto.class);
            return taskDto ;
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto updateStatus(Long id, String status) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(status);
            Task taskupdated =	taskRepository.save(task);
            TaskDto taskDto = modelMapper.map(taskupdated, TaskDto.class);
            return taskDto ;
        }
        return null;
    }
    
    @Override
    public List<TaskDto> findTasksByUserId(Long userId) {
    	List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream()
                    .map(task -> modelMapper.map(task, TaskDto.class))
                    .collect(Collectors.toList());
        
    }
}
