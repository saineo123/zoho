package projectManagementSystem.projectManagement.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import projectManagementSystem.projectManagement.model.AdminProject;

import projectManagementSystem.projectManagement.repository.AdminProjectRepository;

@Service
public class AdminProjectService {
    @Autowired
    private AdminProjectRepository project_repo;

    public List<AdminProject> getAllProjects(){
        return project_repo.findAll();
    }

    @Secured("ROLE_ADMIN")
    public void deleteProject(Long projectId) {
        project_repo.deleteById(projectId);
    } 

    @Secured("ROLE_ADMIN")
    public AdminProject getProjectById(Long projectId){
        Optional<AdminProject> projectOptional = project_repo.findById(projectId);
        return projectOptional.orElse(null);
    }

    @Secured("ROLE_ADMIN")
    public AdminProject saveProjects(AdminProject projects){
        return project_repo.save(projects);
    }
    
    @Secured("ROLE_ADMIN")
    public boolean existsById(final Long aLong){
        return project_repo.existsById(aLong);
    }
}