package ajk.saduzai.mgmj.controller;

import ajk.saduzai.mgmj.domain.Task;
import ajk.saduzai.mgmj.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(
            path = "/getTaskMessage"
    )
    public String test() {
        System.out.println("This is a task controller.");
        return "Successful task";
    }

    @GetMapping("/getTasks")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/findTask/{id}")
    public Task findTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping(
            path = "/createTask",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Task createTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/updateTask/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task currentTask = taskService.findTaskById(id);
        currentTask.setName(task.getName());
        return taskService.save(currentTask);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{taskId}/assign/{memberId}")
    public ResponseEntity<Task> assignTaskToMember(
            @PathVariable Long taskId,
            @PathVariable Long memberId) {
        Task updatedTask = taskService.assignTaskToMember(taskId, memberId);
        return ResponseEntity.ok(updatedTask);
    }
}
