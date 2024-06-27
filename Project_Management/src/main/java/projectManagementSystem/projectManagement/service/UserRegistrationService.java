package projectManagementSystem.projectManagement.service;

import projectManagementSystem.projectManagement.model.UserRegistration;
import projectManagementSystem.projectManagement.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class UserRegistrationService {
    @Autowired
    private UserRegistrationRepository userRepository;

    // Service method to register a new user
    @Transactional
    public UserRegistration registerUser(UserRegistration user) throws Exception {
        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }
        // Save the user to the database
        return userRepository.save(user);
    }
}
