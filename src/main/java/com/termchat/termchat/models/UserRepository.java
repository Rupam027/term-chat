package com.termchat.termchat.models;


import org.springframework.data.repository.CrudRepository ; 
import org.springframework.stereotype.Repository; 
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional ; 


import java.util.* ; 
import com.termchat.termchat.models.UserModel ; 

@Repository
public interface UserRepository extends CrudRepository<UserModel , Long>{

	public UserModel save(UserModel user);


	
	@Query(value = "SELECT * FROM Chatters WHERE name = :name" , nativeQuery=true)
	public List<UserModel> findByName(String name);


	@Transactional
	@Modifying
	@Query(value = "UPDATE Chatters SET session_id = :sid WHERE name = :name" , nativeQuery=true)
	public void updateSession(String sid , String name);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Chatters WHERE name = :name" , nativeQuery=true)
	public void deleteByName(String name);

	
}