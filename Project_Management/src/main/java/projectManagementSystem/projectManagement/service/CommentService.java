package projectManagementSystem.projectManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectManagementSystem.projectManagement.model.Comment;
import projectManagementSystem.projectManagement.repository.CommentRepository;
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // Method to create a comment

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Method to update an existing comment
    public Comment updateComment(Long id, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));

        existingComment.setContent(updatedComment.getContent());
        // Add more fields to update as needed

        return commentRepository.save(existingComment);
    }


    public Iterable<Comment> getComment(){
        return commentRepository.findAll();
    }
}

