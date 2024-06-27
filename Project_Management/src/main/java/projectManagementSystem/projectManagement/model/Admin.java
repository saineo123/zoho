package projectManagementSystem.projectManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * This class represents an entity for storing information about administrators.
 * An administrator entity typically contains information such as username,
 * password, and email address.
 */

@Data // Lombok annotation to generate getters, setters, toString, and other boilerplate code
@Entity // Indicates that this class is a JPA entity, mapped to a database table
public class Admin {
    
    /**
     * The unique identifier for an admin.
     */
    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Long adminId; // The ID of the admin entity
    
    /**
     * The username of the admin.
     */
    private String username; // The username used for authentication
    
    /**
     * The password of the admin.
     */
    private String password; // The password used for authentication
    
    /**
     * The email address of the admin.
     */
    private String email; // The email address associated with the admin
}

