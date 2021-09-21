package com.termchat.termchat.models;


import org.springframework.data.repository.CrudRepository ; 
import org.springframework.stereotype.Repository; 
import org.springframework.data.jpa.repository.Query;


import java.util.* ; 
import com.termchat.termchat.models.UserModel ; 

@Repository
public interface UserRepository extends CrudRepository<UserModel , Long>{

	public UserModel save(UserModel user);

	@Query(value = "SELECT * FROM Chatters WHERE name = :name" , nativeQuery=true)
	public List<UserModel> findByName(String name);

}