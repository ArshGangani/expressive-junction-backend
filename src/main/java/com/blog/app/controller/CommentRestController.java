package com.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.blog.app.entities.Comment;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.services.CommentService;
import com.blog.app.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class CommentRestController {

	@Autowired
    private UserService userService;
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
    
    @GetMapping("/comment/post/{postid}")
    public List<Comment> getByPost(@PathVariable Long postid) {
    	return commentService.getByPost(postid);
    }
    
    @GetMapping("/comment/user/{userid}")
    public List<Comment> getByUser(@PathVariable Long userid) {
    	return commentService.getByUser(userid);
    }

    @PostMapping("/comment")
    public Comment addComment(@RequestBody Comment comment) {
    	if(comment.getUser().getPassword()==null) {
			throw new RuntimeException("give password");
		}
		User user = userService.getUser(comment.getUser().getId());
		if(user==null) {
			throw new RuntimeException("user not found ");
		} else if(!user.getPassword().equals(comment.getUser().getPassword())) {
			throw new RuntimeException("Incorrect password");
		} 
        commentService.addComment(comment);
        comment = getComment(comment.getId());
        return comment;
    }

    @PutMapping("/comment/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        return commentService.updateComment(commentId, comment);
    }

    @DeleteMapping("/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId,@RequestBody User user) {
    	Comment tempComment = commentService.getComment(commentId);
		System.out.println(user);
		if(tempComment==null) {
			throw new RuntimeException("Post id not found - " + commentId); 
		}
		if(user.getPassword()==null) {
			throw new RuntimeException("give user information");
		} else if(!user.getPassword().equals(tempComment.getUser().getPassword())) {
			throw new RuntimeException("Incorrect Password");
		}
        commentService.deleteComment(commentId);
        return "Deleted Post id - " + commentId;
    }
}