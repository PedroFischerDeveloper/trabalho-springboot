package com.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.model.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic,Integer> {

	// Traz os tópicos ordenados pela última data de atividade (tópico criado, post feito no tópico)
	@Query("select t from Topic t left join t.posts p"
			+ " where p.id ="
			+ " (select max(p1.id) from Post p1 left join p1.topic t1 where t1.id = t.id)"
			+ " or p.id is null"
			+ " order by case when p.id is null then t.creationDate else p.creationDate end desc")
	public Iterable<Topic> findAllOrderedByBump();
	
	@Query("from Topic where id = :tid")
	public Topic findById(@Param("tid") Long tid);
	
	public String deleteById(Long id);
}