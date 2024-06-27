package projectManagementSystem.projectManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-one relationship with task
    //@ManyToOne
    //@JoinColumn(name = "task_id")
   // private Task task;

    private String content;

}
