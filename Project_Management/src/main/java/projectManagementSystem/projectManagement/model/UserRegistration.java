package projectManagementSystem.projectManagement.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data // Lombok annotation to generate getters, setters, toString, and hashCode methods
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username should not be blank and should be between 4 and 20 characters long
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    // Email should not be blank and should match the specified pattern for a valid email address
    @NotBlank
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please provide a valid email address")
    private String email;

    // Password should not be blank and should match the specified pattern for a strong password
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
        message = "Password must contain minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;
}
