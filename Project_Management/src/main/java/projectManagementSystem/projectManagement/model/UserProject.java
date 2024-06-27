package projectManagementSystem.projectManagement.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserProject  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String name;
    private String assignedTo;

    // Constructors, getters, and setters
    // (Assuming getters and setters are generated)
    
}