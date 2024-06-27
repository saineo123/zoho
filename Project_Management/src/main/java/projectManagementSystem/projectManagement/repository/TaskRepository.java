package projectManagementSystem.projectManagement.repository;

import projectManagementSystem.projectManagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
