package com.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.User;
import com.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired 
	UserRepository repo;
	
	public List<User> getUsers() {
		List<User> users = StreamSupport.stream(repo.findAll().spliterator(), false)
			    .collect(Collectors.toList());
		return users;
	}
	
	public User getById(long id) {
		return repo.findById(id);
	}
	
	public User getByToken(String token) {
		return repo.findByToken(token);
	}
	
	public User insert(User u) throws Exception {
		if(!isValidEmailAddress(u.email))
			throw new Exception("Email inválido");
		
		return repo.save(u);
	}
	
	
	private boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}
