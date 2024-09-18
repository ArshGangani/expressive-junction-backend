package com.blog.app.DAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.blog.app.entities.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class PostDAO {

	private static EntityManager entityManager;
	
	@Autowired
	public PostDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	public List<Post> findAll() {

		// create a query
		TypedQuery<Post> theQuery = 
				entityManager.createQuery("from Post", Post.class);
		
		// execute query and get result list
		List<Post> post = theQuery.getResultList();
		
		// return the results		
		return post;
	}
	
	public Post findById(int theId) {

		Post post = 
				entityManager.find(Post.class, theId);
		return post;
	}
	
	public Post save(Post post) {
		Post dbPost = entityManager.merge(post);
		return dbPost;
	}
	
	public void deleteById(int theId) {

		// retrieve the category
        Post post = entityManager.find(Post.class, theId);
        entityManager.remove(post);
		
	}

	public static List<Post> findByCategory(int categoryid) {
		String query = "SELECT p FROM Post p JOIN p.categories c WHERE c.id = :categoryId";
        return entityManager.createQuery(query, Post.class).setParameter("categoryId", categoryid).getResultList();
	}

	public static List<Post> findByUser(int userid) {
		String query = "SELECT p from Post p JOIN p.author a where a.id = :userid";
		return entityManager.createQuery(query,Post.class).setParameter("userid", userid).getResultList();
	}
	
	
}