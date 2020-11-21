package com.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_post")
public class Post implements Serializable{
	
	// public only for teste
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter public long id; 

	@Column(nullable = false)
	@Getter @Setter public String content;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss dd/MM/yyyy", timezone = "America/Sao_Paulo")
	@JsonProperty(access = Access.READ_ONLY)
	@Getter @Setter public Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss dd/MM/yyyy", timezone = "America/Sao_Paulo")
	@JsonProperty(access = Access.READ_ONLY)
	@Getter @Setter public Date updateDate;
	
	@ManyToOne
	@Getter @Setter public User user;
	
	@ManyToOne
	@JsonIgnore
	@Getter @Setter public Topic topic;
}
