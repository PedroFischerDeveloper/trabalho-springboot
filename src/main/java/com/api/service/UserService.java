package com.api.service;

import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.api.model.User;
import com.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired 
	private UserRepository repo;
	
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
		if(u.password.length() < 5)
			throw new Exception("Senha precisa ter 6 ou mais caracteres.");
			
		u.password = MD5(u.password);
		
		return repo.save(u);
	}
	
	public String login(User credentials) throws Exception {
		User loggingUser = repo.findByEmail(credentials.email);
		
		if(loggingUser != null) {
			if(MD5(credentials.password) != loggingUser.password) {
				Random r = new Random();
				byte[] b = new byte[30];
				r.nextBytes(b);
				
				String token = new String(Base64.getEncoder().encode(b));
				//repo.addToken(loggingUser.id, token);
				loggingUser.token = token;
				repo.save(loggingUser);
				return token;
			}
		}
		
		throw new Exception("Usuário ou senha inválidos");
	}
	
	public void logout(User u) {
		u.token = null;
		repo.save(u);
	}
	
	private String MD5(String str) {
		return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
	}
	
	private boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}
