package com.api.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
public class HomeController {

	
	@GetMapping("")
	public String index()
	{
		return "API version 0.1.1";
	}
}
