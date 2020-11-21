package com.api.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Post;
import com.api.model.TopicStatus;
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
		if(post.topic.status == TopicStatus.CLOSED)
			throw new Exception("Tópico indisponível");
		post.creationDate = new Date();
		return repo.save(post);
	}
	
	public Post upate(Post oldData, Post newData, long id) throws Exception {
		if(oldData.topic.status == TopicStatus.CLOSED)
			throw new Exception("Tópico indisponível");
		oldData.content = newData.content;
		oldData.updateDate = new Date();
		return repo.save(oldData);
	}
	
	public void delete(Post post) throws Exception {
		repo.delete(post);
	}
}
