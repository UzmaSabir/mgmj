package ajk.saduzai.mgmj.service;

import ajk.saduzai.mgmj.domain.Task;
import ajk.saduzai.mgmj.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task save(Task Task) {
        return taskRepository.save(Task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

}
