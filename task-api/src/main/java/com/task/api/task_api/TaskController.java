package com.task.api.task_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.api.task_api.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTasks() {
        try {
            List<Task> tasks = taskRepository.findAll();
            return ResponseEntity.ok(new ApiResponse(true, "Tasks retrieved successfully", tasks));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error retrieving tasks", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable Long id) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

            return ResponseEntity.ok(new ApiResponse(true, "Task retrieved successfully", task));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Error retrieving task with id: " + id, null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Task created successfully", createdTask));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error creating task", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

            task.setTitle(taskDetails.getTitle());
            task.setStatus(taskDetails.getStatus());

            Task updatedTask = taskRepository.save(task);

            return ResponseEntity.ok(new ApiResponse(true, "Task updated successfully", updatedTask));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Error updating task with id: " + id, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Long id) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

            taskRepository.delete(task);
            return ResponseEntity.ok(new ApiResponse(true, "Task deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Error deleting task with id: " + id, null));
        }
    }
    
}
