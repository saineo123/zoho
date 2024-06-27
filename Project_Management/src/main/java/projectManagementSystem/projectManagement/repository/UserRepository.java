package projectManagementSystem.projectManagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectManagementSystem.projectManagement.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
} 

