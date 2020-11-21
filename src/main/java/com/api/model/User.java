package com.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
public class User implements Serializable{
	
	// public only for teste
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter public long id; 
	
	@Getter @Setter public String name;
	
	@Column(unique=true)
	@Getter @Setter public String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Getter @Setter public String password;
	
	@Column(unique=true)
	@JsonProperty(access = Access.WRITE_ONLY)
	@Getter @Setter public String token;
}
