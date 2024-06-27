package projectManagementSystem.projectManagement.controller;

// libraries
import java.util.HashMap;
import java.util.Map;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.FieldError;

import projectManagementSystem.projectManagement.model.UserRegistration;
import projectManagementSystem.projectManagement.service.UserRegistrationService;

@RestController
@RequestMapping("/user")
@Validated
public class UserRegistrationController {
    @Autowired
    private UserRegistrationService userService;

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistration user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {

            // If an exception occurs during registration, return a BAD_REQUEST status with the exception message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Exception handler for MethodArgumentNotValidException
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        // Create a map to hold validation errors
        Map<String, String> errorMap = new HashMap<>();

        // Iterate through validation errors and populate the error map
        e.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });

        // Return the error map along with a BAD_REQUEST status
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
