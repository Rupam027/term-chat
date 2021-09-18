package com.termchat.termchat.models;


import org.springframework.data.repository.CrudRepository ; 
import org.springframework.stereotype.Repository ; 

import java.util.* ; 
import com.termchat.termchat.models.UserModel ; 

@Repository
public interface UserRepository extends CrudRepository<UserModel , Long>{

	public List<UserModel> findAllUser(long id);

}