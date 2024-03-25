package com.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.blog.app.entities.Comment;
import com.blog.app.services.CommentService;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/comment/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/comment")
    public Comment addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        comment = getComment(comment.getId());
        return comment;
    }

    @PutMapping("/comment/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        return commentService.updateComment(commentId, comment);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}