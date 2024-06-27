package projectManagementSystem.projectManagement.controller;

// import all the projects
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.ChronoUnit;

import projectManagementSystem.projectManagement.Exceptions.DateException;
import projectManagementSystem.projectManagement.Exceptions.ProjectNotFound;
import projectManagementSystem.projectManagement.model.AdminProject;
import projectManagementSystem.projectManagement.service.AdminProjectService;

// call the rest controller for api calls
@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/auth/projects") // crete a prefix /auth/projects
public class AdminProjectController {

    // initialize the project services
    @Autowired
    private AdminProjectService projectService;

    // create a get request
    @GetMapping
    @Secured("ROLE_ADMIN") // only admin can access this api call
    public ResponseEntity<List<AdminProject>> getAllProjects(){     // create a function getAllProjects which return the lists of all projects
        List<AdminProject> projects = projectService.getAllProjects(); // get all the projects
        return ResponseEntity.ok(projects); // once the request is valid, return the status code of OK,(200)
    }

    // create a delete request
    @DeleteMapping("/{projectId}") 
    @Secured("ROLE_ADMIN") 
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) { // create a function deleteProject, now the return type of this
        // project not defined explicitly, here '?', 
        // means the return type can be anything
        // use try catch to handle exception at runtime
        try {
            if (projectService.existsById(projectId)) { // if the project exists, then go ahead and delete it
                projectService.deleteProject(projectId); // use the deleteProject function to delete the project
                return ResponseEntity.ok().build(); // then finally return the status, however we have something called .build(), which gives us result , and not just http status code
            } else {
                throw new ProjectNotFound("This project does not exist");
                //return ResponseEntity.notFound().build(); // if the project is not found then return the .notFound() status code, again return the string using .build()
            }
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // and if there no proper project to delete
        }
    }

    // project duration 
    @GetMapping("/{projectId}/duration")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getProjectDuration(@PathVariable Long projectId) {
        AdminProject project = projectService.getProjectById(projectId);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
    
        // Convert Date to LocalDate
        LocalDate localStartDate = project.getStartDate();
        LocalDate localEndDate = project.getEndDate();
    
        // Calculate the duration between the two dates
        long totalDays = ChronoUnit.DAYS.between(localStartDate, localEndDate);
    
        // Calculate duration 
        String duration = totalDays+" days";
    
        return ResponseEntity.ok(duration);
    }

    // creatting projects
    @PostMapping("/create")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> createProject(@RequestBody AdminProject projects){
        try {
            AdminProject createProject = projectService.saveProjects(projects);
            if(projects.getEndDate().isAfter(projects.getStartDate())){
                return new ResponseEntity<Object>(createProject,HttpStatus.CREATED);
            }
            else{
                throw new DateException("End date should be valid chronologically");
            }
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
