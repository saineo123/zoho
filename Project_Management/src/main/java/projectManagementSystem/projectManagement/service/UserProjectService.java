package projectManagementSystem.projectManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectManagementSystem.projectManagement.model.UserProject;

import projectManagementSystem.projectManagement.repository.UserProjectRepository;

import java.util.List;

@Service
public class UserProjectService {

    @Autowired
    private UserProjectRepository projectRepository;

    // Method to retrieve projects assigned to a team member
    public List<UserProject> getProjectsAssignedTo(String assignedTo) {
        return projectRepository.findByAssignedTo(assignedTo);
    }

    // Method to create a new project
    public UserProject createProject(UserProject project) {
        return projectRepository.save(project);
    }

    // Method to retrieve all projects
    public Iterable<UserProject> getAllProjects() {
        return projectRepository.findAll();
    }

    // Method to update an existing project
    public UserProject updateProject(Long projectId, UserProject updatedProject) {
        UserProject existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        existingProject.setName(updatedProject.getName());
        existingProject.setAssignedTo(updatedProject.getAssignedTo());
        // Add more fields to update as needed

        return projectRepository.save(existingProject);
    }



    public boolean deleteProject(Long projectId) {
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
            return true;
        } else {
            // Handle project not found
            return false;
        }
    }
}
