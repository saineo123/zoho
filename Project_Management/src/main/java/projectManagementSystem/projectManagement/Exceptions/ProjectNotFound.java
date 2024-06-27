package projectManagementSystem.projectManagement.Exceptions;

public class ProjectNotFound extends RuntimeException{
    public ProjectNotFound(String message){
        super(message);
    }
}
