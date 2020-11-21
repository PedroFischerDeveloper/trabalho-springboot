package com.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
	
	@Query("from User where id = :userid")
	public User findById(@Param("userid") Long userid);

	@Query("from User where token = :token")
	public User findByToken(@Param("token") String token);
	
	public String deleteById(Long id);
}