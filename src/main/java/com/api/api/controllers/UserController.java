package com.api.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.User;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/users")
public class UserController {
	
	
	@GetMapping("")
	public List<User> index()
	{
		List<User> list = new ArrayList<User>();
		
		for(int i = 0; i < 21; i++) 
		{
			User user = new User();
			
			user.id = i;
			user.name = "Exemple " + i ;
			user.email = "exemple@email"+i+".com";
			
			list.add(user);	
		}
		
		return list;	
		
	}
	
	@GetMapping("{id}")
	public String finById(@PathVariable(value = "id") long id)
	{
		
		return "users by " + id;
	}
	
	
	@PostMapping
	public String save()
	{
		return "save a user ";
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
