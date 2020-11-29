package com.api.internal;

import java.io.Serializable;

import com.api.model.User;

import lombok.Getter;

public class UserToken implements Serializable{
	
	@Getter private long id;
	@Getter private String nome;
	@Getter private String email;
	@Getter private String seed;
	
	public UserToken(User user, String seed) {
		id = user.id;
		nome = user.name;
		email = user.email;
		this.seed = seed;
	}

}
