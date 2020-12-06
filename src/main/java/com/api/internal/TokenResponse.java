package com.api.internal;

import java.io.Serializable;

import com.api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class TokenResponse implements Serializable{

	@JsonProperty(value = "token")
	@Getter private String token;
	
	public TokenResponse(String token) {
		this.token = token;
	}

}
