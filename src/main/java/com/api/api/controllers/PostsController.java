package com.api.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/posts")
public class PostsController {
	
	
	@GetMapping("")
	public String index()
	{
		return "posts all";
	}
	
	@GetMapping("{id}")
	public String finById(@PathVariable(value = "id") long id)
	{
		return "post by " + id;
	}
	
	
	@PostMapping
	public String save()
	{
		return "save a post ";
	}
	
	
	@PutMapping
	public String update(@PathVariable(value = "id") long id)
	{
		return "update a post by " + id;
	}
	
	
	@DeleteMapping
	public String delete()
	{
		return "delete a post by id ";
	}
}
