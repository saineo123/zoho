package projectManagementSystem.projectManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projectManagementSystem.projectManagement.model.UserProject;
import projectManagementSystem.projectManagement.service.UserProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class UserProjectController {

    @Autowired
    private UserProjectService projectService;

    // Endpoint to create a new project
    @PostMapping
    public ResponseEntity<UserProject> createProject(@RequestBody UserProject project) {
        UserProject createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Endpoint to retrieve projects assigned to a team member
    @GetMapping("/{assignedTo}")
    public ResponseEntity<List<UserProject>> getProjectsAssignedTo(@PathVariable String assignedTo) {
        List<UserProject> projects = projectService.getProjectsAssignedTo(assignedTo);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Endpoint to retrieve all projects
    @GetMapping
    public ResponseEntity<Iterable<UserProject>> getAllProjects() {
        Iterable<UserProject> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Endpoint to update an existing project
    @PutMapping("/{projectId}")
    public ResponseEntity<UserProject> updateProject(@PathVariable Long projectId, @RequestBody UserProject updatedProject) {
        UserProject updated = projectService.updateProject(projectId, updatedProject);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Endpoint to delete a project
    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId) {
        boolean deleted = projectService.deleteProject(projectId);
        if (deleted) {
            return new ResponseEntity<>("Project deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Project not found or unable to delete", HttpStatus.NOT_FOUND);
        }
    }
}
