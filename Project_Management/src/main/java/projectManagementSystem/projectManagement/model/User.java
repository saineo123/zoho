package projectManagementSystem.projectManagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@NoArgsConstructor

// user login entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String Email;
    private String password;
 
 
}