package com.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.api.internal.ErrorMessage;
import com.api.model.User;
import com.api.repository.UserRepository;
import com.api.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("")
	public ResponseEntity<List<User>> index()
	{
		return ResponseEntity.ok(service.getUsers());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> findById(@PathVariable(value = "id") long id)
	{
		User user = service.getById(id);
		if(user == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(user);
	}
	
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User obj)
	{
		try {
			User user = service.insert(obj);
				
			return ResponseEntity.ok(user);
		}
		catch(Exception ex){
			return new ResponseEntity(new ErrorMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("login")
	public ResponseEntity<User> login(@RequestBody User obj)
	{
		try {
			String token = service.login(obj);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", token);
			
			return new ResponseEntity(service.getByToken(token), headers, HttpStatus.OK);
		}
		catch(Exception ex){
			return new ResponseEntity(new ErrorMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("logout")
	public ResponseEntity<User> logout(@RequestHeader("Authorization") String token)
	{
		try {
			User u = service.getByToken(token);
			if(u!= null) {
				service.logout(u);
				return new ResponseEntity(HttpStatus.OK);
			}
			else {
				return new ResponseEntity(new ErrorMessage("Token inválido."),HttpStatus.UNAUTHORIZED);
			}
		}
		catch(Exception ex){
			return new ResponseEntity(new ErrorMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping
	public String update(@PathVariable(value = "id") long id)
	{
		return "update a user by " + id;
	}
	
	
	@DeleteMapping
	public String delete()
	{
		return "delete a user by id ";
	}
}
