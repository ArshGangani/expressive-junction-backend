package com.blog.app.services;

import com.blog.app.DAO.CommentDAO;
import com.blog.app.entities.Comment;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private PostService postService;
    
    @Transactional
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    @Transactional
    public Comment getComment(Long commentId) {
    	Comment comment = commentDAO.getComment(commentId);
		if (comment == null) {
            throw new EntityNotFoundException("Comment with ID " + commentId + " not found");
        }
        return comment;
    }

    @Transactional
    public Comment addComment(Comment comment) {
    	try {
    		return commentDAO.addComment(comment);
		}
		catch (EntityExistsException ex) {
	    	throw new EntityExistsException("Comment already exists: " + ex.getMessage());
		}
    }

    @Transactional
    public Comment updateComment(Long commentId, Comment updatedComment) {
    	Comment existingComment = commentDAO.getComment(commentId);
        if (existingComment == null) {
            throw new EntityNotFoundException("Comment with ID " + commentId + " not found");
        }
        return commentDAO.updateComment(commentId, updatedComment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
    	Comment existingComment = commentDAO.getComment(commentId);
        if (existingComment == null) {
            throw new EntityNotFoundException("Comment with ID " + commentId + " not found");
        }
        commentDAO.deleteComment(commentId);
    }

	public List<Comment> getByPost(Long postid) {
		return commentDAO.getByPost(postid);
	}

	public List<Comment> getByUser(Long userid) {
		return commentDAO.getByUser(userid);
	}
}
