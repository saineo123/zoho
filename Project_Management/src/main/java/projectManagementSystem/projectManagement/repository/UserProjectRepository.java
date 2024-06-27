package projectManagementSystem.projectManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projectManagementSystem.projectManagement.model.UserProject;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
    List<UserProject> findByAssignedTo(String assignedTo);
}
