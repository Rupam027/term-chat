package com.termchat.termchat.models;


import org.springframework.data.repository.CrudRepository ; 
import org.springframework.stereotype.Repository; 
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional ; 


import java.util.* ; 
import com.termchat.termchat.models.RoomModel ; 


@Repository
public interface RoomRepository extends CrudRepository<RoomModel , Long>{


	

	@Query(value = "SELECT * FROM room WHERE name = :room_name and created_by = :creator" , nativeQuery=true)
	public List<RoomModel> findRoom(String room_name , String creator);


}