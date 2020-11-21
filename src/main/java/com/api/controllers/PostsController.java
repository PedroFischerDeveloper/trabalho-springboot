package com.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.internal.ErrorMessage;
import com.api.model.Post;
import com.api.model.Topic;
import com.api.model.TopicStatus;
import com.api.model.User;
import com.api.service.PostService;
import com.api.service.TopicService;
import com.api.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/posts")
public class PostsController {

	@Autowired
	private PostService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TopicService topicService;
	
	
	@GetMapping("topic/{id}")
	public ResponseEntity<List<Post>> index(@PathVariable(value = "id") long id)
	{
		return ResponseEntity.ok(service.getPostsFromTopic(id));
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Post> findById(@PathVariable(value = "id") long id)
	{
		Post post = service.getById(id);
		if(post == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);

		return ResponseEntity.ok(post);
	}
	
	
	@PostMapping("topic/{id}")
	public ResponseEntity<Post> save(@RequestBody Post obj, @RequestHeader("Authorization") String token,
			@PathVariable(value = "id") long id)
	{
		try {
			User u = userService.getByToken(token);
			Topic t = topicService.getById(id);
			if(u == null) {
				return new ResponseEntity(new ErrorMessage("Token inválido."),HttpStatus.UNAUTHORIZED);
			}
			else if(t == null){
				return new ResponseEntity(new ErrorMessage("Tópico não encontrado."),HttpStatus.NOT_FOUND);
			}
			else {
				obj.user = u;
				obj.topic = t;
				
				Post post = service.insert(obj);
				return ResponseEntity.ok(post);
			}
		}
		catch(Exception ex) {
			return new ResponseEntity(new ErrorMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<Post> update(@RequestBody Post obj, @RequestHeader("Authorization") String token,
			@PathVariable(value = "id") long id)
	{
		try {
			User u = userService.getByToken(token);
			Post p = service.getById(id);
			if(p == null) {
				return new ResponseEntity(new ErrorMessage("Post não encontrado."),HttpStatus.NOT_FOUND);
			}
			else if(u == null || p.user.id != u.id){
				return new ResponseEntity(new ErrorMessage("Token inválido."),HttpStatus.UNAUTHORIZED);
			}
			else {
				p = service.upate(p, obj, id);
				return ResponseEntity.ok(p);
			}
		}
		catch(Exception ex) {
			return new ResponseEntity(new ErrorMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@RequestHeader("Authorization") String token, @PathVariable(value = "id") long id)
	{
		try {
			User u = userService.getByToken(token);
			Post p = service.getById(id);
			if(p == null) {
				return new ResponseEntity(new ErrorMessage("Post não encontrado."),HttpStatus.NOT_FOUND);
			}
			else if(u == null || p.user.id != u.id){
				return new ResponseEntity(new ErrorMessage("Token inválido."),HttpStatus.UNAUTHORIZED);
			}
			else {
				service.delete(p);
				return ResponseEntity.ok(p);
			}
		}
		catch(Exception ex) {
			return new ResponseEntity(new ErrorMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
