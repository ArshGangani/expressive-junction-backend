package com.blog.app.services;

import com.blog.app.DAO.CommentDAO;
import com.blog.app.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Transactional
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    @Transactional
    public Comment getCommentById(Long id) {
        return commentDAO.getCommentById(id);
    }

    @Transactional
    public Comment addComment(Comment comment) {
        return commentDAO.addComment(comment);
    }

    @Transactional
    public Comment updateComment(Long id, Comment comment) {
        return commentDAO.updateComment(id, comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentDAO.deleteComment(id);
    }
}
