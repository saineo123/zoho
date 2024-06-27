package projectManagementSystem.projectManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String priority;
    private String status;
    @Builder.Default
    private Date startDate = new Date();
    private LocalDate endDate;
    private int workingHoursPerDay;
    private boolean closed;

    @JsonManagedReference
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Assignee> assignees = new HashSet<>();

    public void setEndDate(LocalDate endDate) {
        // Check if the provided end date is yesterday's date
        LocalDate yesterday = LocalDate.now().minusDays(1);
        if (endDate.isBefore(yesterday)) {
            // If it is, throw an IllegalArgumentException with an error message
            throw new IllegalArgumentException("End date cannot be set to yesterday's date.");
        } else {
            // Otherwise, set it to the provided end date
            this.endDate = endDate;
        }
    }
}
