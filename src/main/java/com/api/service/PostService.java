package com.api.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Post;
import com.api.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired 
	private PostRepository repo;
	
	public List<Post> getPostsFromTopic(long id) {
		List<Post> posts = StreamSupport.stream(repo.findPostsFromTopic(id).spliterator(), false)
			    .collect(Collectors.toList());
		return posts;
	}
	
	public Post getById(long id) {
		return repo.findById(id);
	}
	
	public Post insert(Post post) throws Exception {
		post.creationDate = new Date();
		return repo.save(post);
	}
}
