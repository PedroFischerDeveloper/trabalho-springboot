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
@RequestMapping(value="/api/topics")
public class TopicsController {
	
	
	@GetMapping("")
	public String index()
	{
		return "users all";
	}
	
	@GetMapping("{id}")
	public String finById(@PathVariable(value = "id") long id)
	{
		return "topics by " + id;
	}
	
	
	@PostMapping
	public String save()
	{
		return "save a topic ";
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
