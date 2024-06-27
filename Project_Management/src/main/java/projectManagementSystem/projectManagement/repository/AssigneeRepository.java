package projectManagementSystem.projectManagement.repository;

import projectManagementSystem.projectManagement.model.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigneeRepository extends JpaRepository<Assignee, Long> {

    void deleteByTaskId(Long taskId);
}
