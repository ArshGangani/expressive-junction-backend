package com.blog.app.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.blog.app.entities.Comment;

import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
public class CommentDAO {

	@Autowired
    private EntityManager entityManager;

    public List<Comment> getAllComments() {
        return entityManager.createQuery("FROM Comment", Comment.class).getResultList();
    }

    public Comment getComment(Long commentId) {
        return entityManager.find(Comment.class, commentId);
    }

    // Add & also Update Comment
    public Comment addComment(Comment comment) {
        entityManager.merge(comment);
        return comment;
    }

    public Comment updateComment(Long commentId, Comment updatedComment) {
    	updatedComment.setId(commentId);
        return entityManager.merge(updatedComment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = entityManager.find(Comment.class, commentId);
        entityManager.remove(comment);
    }
}