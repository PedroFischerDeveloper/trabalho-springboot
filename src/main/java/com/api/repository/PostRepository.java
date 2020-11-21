package com.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post,Integer> {

	@Query("select p from Post p inner join p.topic t where t.id = :tid")
	public Iterable<Post> findPostsFromTopic(@Param("tid") Long tid);
	
	@Query("from Post where id = :pid")
	public Post findById(@Param("pid") Long pid);
	
	public String deleteById(Long id);
}