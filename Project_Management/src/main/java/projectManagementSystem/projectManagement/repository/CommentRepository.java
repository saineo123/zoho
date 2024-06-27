package projectManagementSystem.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projectManagementSystem.projectManagement.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

