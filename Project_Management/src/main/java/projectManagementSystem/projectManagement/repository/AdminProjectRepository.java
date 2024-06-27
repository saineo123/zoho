package projectManagementSystem.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projectManagementSystem.projectManagement.model.AdminProject;

@Repository
public interface AdminProjectRepository extends JpaRepository<AdminProject,Long>{
    
}