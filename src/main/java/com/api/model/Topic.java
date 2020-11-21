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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_thread")
public class Topic implements Serializable{
	
	// public only for teste
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter public long id; 

	@Column(nullable = false)
	@Getter @Setter public String subject;

	@Column(nullable = false)
	@Getter @Setter public String content;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss dd/MM/yyyy", timezone = "America/Sao_Paulo")
	@Getter @Setter public Date creationDate;
	
	@ManyToOne
	@Getter @Setter public User user;
}
