package com.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
import com.api.model.Topic;
import com.api.model.User;
import com.api.service.TopicService;
import com.api.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/topics")
public class TopicsController {

	@Autowired
	private TopicService service;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("")
	public ResponseEntity<List<Topic>> index()
	{
		return ResponseEntity.ok(service.getTopics());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Topic> findById(@PathVariable(value = "id") long id)
	{
		Topic topic = service.getById(id);
		if(topic == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return ResponseEntity.ok(topic);
	}
	
	
	@PostMapping
	public ResponseEntity<Topic> save(@RequestBody Topic obj, @RequestHeader("Authorization") String token)
	{
		try {
			User u = userService.getByToken(token);
			if(u != null) {
				obj.user = u;
				
				Topic topic = service.insert(obj);
				return ResponseEntity.ok(topic);
			}
			else {
				return new ResponseEntity(new ErrorMessage("Token inválido."),HttpStatus.UNAUTHORIZED);
			}
		}
		catch(Exception ex) {
			return new ResponseEntity(new ErrorMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping
	public String update(@PathVariable(value = "id") long id)
	{
		return "update a topic by " + id;
	}
	
	
	@DeleteMapping
	public String delete()
	{
		return "delete a topic by id ";
	}
}
