package projectManagementSystem.projectManagement.controller;

import projectManagementSystem.projectManagement.Exceptions.TaskNotFoundException;
import projectManagementSystem.projectManagement.model.Assignee;
import projectManagementSystem.projectManagement.model.Task;
import projectManagementSystem.projectManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Create a new task
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Create a new assignee
    @PostMapping("/assignees/create")
    public ResponseEntity<Assignee> createAssignee(@RequestBody Assignee assignee) {
        Assignee createdAssignee = taskService.createAssignee(assignee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAssignee);
    }

    // Add an assignee to a task
    @PostMapping("/{taskId}/assignees")
    public ResponseEntity<String> addAssigneeToTask(@PathVariable Long taskId, @RequestBody Assignee assignee) {
        try {
            Task updatedTask = taskService.assignAssigneeToTask(taskId, assignee);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added Assignee To Task");
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + taskId);
        }
    }

    // Get all assignees
    @GetMapping("/assignees")
    public ResponseEntity<Iterable<Assignee>> getAllAssignees() {
        Iterable<Assignee> assignees = taskService.getAllAssignees();
        return ResponseEntity.ok(assignees);
    }

    // Get assignee by ID
    @GetMapping("/assignees/{id}")
    public ResponseEntity<String> getAssigneeById(@PathVariable Long id) {
        Optional<Assignee> assignee = taskService.getAssigneeById(id);
        if (!assignee.isPresent()) {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("found", HttpStatus.OK);
        }

    }

    // Update an assignee
    @PutMapping("/assignees/{id}")
    public ResponseEntity<Assignee> updateAssignee(@PathVariable Long id, @RequestBody Assignee updatedAssignee) {
        try {
            Assignee assignee = taskService.updateAssignee(id, updatedAssignee);
            return ResponseEntity.ok(assignee);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an assignee
    @DeleteMapping("/assignees/{id}")
    public ResponseEntity<String> deleteAssignee(@PathVariable Long id) {
        boolean deleted = taskService.deleteAssignee(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Assignee with ID " + id + " was deleted.");
        }
    }

    // // Delete an assignee
    // @DeleteMapping("/assignees/{id}")
    // public ResponseEntity<String> deleteAssignee(@PathVariable Long id) {
    //     boolean deleted = taskService.deleteAssignee(id);
    //     if (deleted) {
    //         return ResponseEntity.noContent().build();
    //     } else {
    //         if (taskService.isTaskDeleted(id)) {
    //             return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                     .body("The task was deleted previously. Enter the task ID to delete.");
    //         } else {
    //             return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                     .body("Task with ID " + id + " was not found.");
    //         }
    //     }
    // }

    // Get all tasks
    @GetMapping("/tasks")
    public ResponseEntity<Iterable<Task>> getAllTasks() {
        Iterable<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Get task by ID
    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Task with ID " + id + " was not found.");
        }
    }

    // Update a task
    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        try {
            Task task = taskService.updateTask(id, updatedTask);
            return ResponseEntity.ok(task);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cannot update task: Task not found with ID " + id);
        }
    }

    // Delete a task
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Task with ID " + id + " was deleted.");
        }
    }

    //mark task as closed
    @PutMapping("/{id}")
    public ResponseEntity<Task> markTaskAsClosed(@PathVariable Long id) {
        Task closedTask = taskService.markTaskAsClosed(id);
        if (closedTask != null) {
            return new ResponseEntity<>(closedTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
