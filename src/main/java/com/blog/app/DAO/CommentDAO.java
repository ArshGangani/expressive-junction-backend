package com.blog.app.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.blog.app.entities.Comment;
import com.blog.app.entities.User;

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

	public List<Comment> getByPost(Long postid) {
		String query = "Select c from Comment c JOIN c.post p WHERE p.id = :postid";
		return entityManager.createQuery(query,Comment.class).setParameter("postid", postid).getResultList();
	}

	public List<Comment> getByUser(Long userid) {
		User user = entityManager.find(User.class, userid);
		String query = "SELECT c FROM Comment c WHERE c.user = :user";
		return entityManager.createQuery(query,Comment.class).setParameter("user", user).getResultList();
	}
}