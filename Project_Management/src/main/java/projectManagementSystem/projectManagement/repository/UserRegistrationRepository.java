package projectManagementSystem.projectManagement.repository;


import projectManagementSystem.projectManagement.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {

    // Find a user by their username
    Optional<UserRegistration> findByUsername(String username);

    // Find a user by their email address
    Optional<UserRegistration> findByEmail(String email);
}


