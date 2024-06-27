package projectManagementSystem.projectManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectManagementSystem.projectManagement.Exceptions.TaskNotFoundException;
import projectManagementSystem.projectManagement.model.Assignee;
import projectManagementSystem.projectManagement.model.Task;
import projectManagementSystem.projectManagement.repository.AssigneeRepository;
import projectManagementSystem.projectManagement.repository.TaskRepository;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AssigneeRepository assigneeRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Assignee createAssignee(Assignee assignee) {
        return assigneeRepository.save(assignee);
    }

    public Task assignAssigneeToTask(Long taskId, Assignee assignee) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));
        assignee.setTask(task);
        task.getAssignees().add(assignee);
        return taskRepository.save(task);
    }

    public Iterable<Assignee> getAllAssignees() {
        return assigneeRepository.findAll();
    }

    public Optional<Assignee> getAssigneeById(Long id) {
        return assigneeRepository.findById(id);
    }

    public Assignee updateAssignee(Long id, Assignee updatedAssignee) throws TaskNotFoundException {
        Assignee assignee = assigneeRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Assignee not found with ID: " + id));
        assignee.setName(updatedAssignee.getName());
        assignee.setEmail(updatedAssignee.getEmail());
        // Update other fields as needed
        return assigneeRepository.save(assignee);
    }

    public boolean deleteAssignee(Long id) {
        if (assigneeRepository.existsById(id)) {
            assigneeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task updatedTask) throws TaskNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
        task.setName(updatedTask.getName());
        task.setDescription(updatedTask.getDescription());
        // Update other fields as needed
        return taskRepository.save(task);
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean isTaskDeleted(Long id) {
        // Check if the task was previously deleted
        return !taskRepository.existsById(id);
    }

    public Task markTaskAsClosed(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setClosed(true);
            return taskRepository.save(task);
        }
        return null;
    }
}
