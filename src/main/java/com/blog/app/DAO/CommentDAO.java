package com.blog.app.DAO;

import org.springframework.stereotype.Repository;
import com.blog.app.entities.Comment;

import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
public class CommentDAO {

    private EntityManager entityManager;

    public CommentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Comment> getAllComments() {
        return entityManager.createQuery("FROM Comment", Comment.class).getResultList();
    }

    public Comment getCommentById(Long id) {
        return entityManager.find(Comment.class, id);
    }

    public Comment addComment(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        Comment existingComment = entityManager.find(Comment.class, id);
        if (existingComment != null) {
            existingComment.setContent(updatedComment.getContent());
            existingComment.setCommentDate(updatedComment.getCommentDate());
            entityManager.merge(existingComment);
        }
        return existingComment;
    }

    public void deleteComment(Long id) {
        Comment comment = entityManager.find(Comment.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }
}
