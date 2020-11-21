package com.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.model.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic,Integer> {
	
	@Query("from Topic where id = :tid")
	public Topic findById(@Param("tid") Long tid);
	
	public String deleteById(Long id);
}