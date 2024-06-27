package projectManagementSystem.projectManagement.Exceptions;

public class AdminDoesNotExist extends RuntimeException{
    public AdminDoesNotExist(String message){
        super(message);
    }
}
