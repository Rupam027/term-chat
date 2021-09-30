package com.termchat.termchat.controllers; 

import org.springframework.web.bind.annotation.* ;
import org.springframework.beans.factory.annotation.Autowired ; 


import org.springframework.http.MediaType ; 
import com.termchat.termchat.models.* ; 
import java.util.*;













@RestController
public class Room{


	@Autowired RoomRepository room_repo ; 
	@Autowired UserRepository user_repo ; 

	class Response{

		private String status ; 
		public Response(String status){

			this.status = status ; 
		}

		public void setStatus(String status){

			this.status = status ; 
		}		

		public String getStatus(){
			return this.status ; 
		}


	}



	@GetMapping(value = "/create/{creator}/{session_id}/{room_name}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object create_room(@PathVariable("creator") String creator , @PathVariable("room_name") String room_name , @PathVariable("session_id") String session_id){

		List<UserModel> userList = user_repo.findByName(creator) ; 
		List<RoomModel> roomList = room_repo.findRoom(room_name , creator);
		if(userList.size() == 0)
			return new Response("Unauthenticated");

		if(userList.get(0).getSession_id() == null)
			return new Response("Logged Out");

		if (!userList.get(0).getSession_id().equals(session_id))
			return new Response("Invalid_Session");

		if(roomList.size() == 1)
			return new Response("Already Created Room");

		Date date = new Date();
		String created_on = date.toString();
		String ri = creator  + "@" + room_name ; 

		RoomModel room = new RoomModel();
		room.setName(room_name);
		room.setUser(userList.get(0));
		room.setCreated_by(creator);
		room.setCreated_on(created_on);
		
		room_repo.save(room) ;  
		
		return new Response("Created Room " + room_name + "with Room Identifier " + creator + "@" + room_name);


	}



	@GetMapping(value = "/join/{user}/{session_id}/{room_identifier}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object join_room(@PathVariable("user") String user , @PathVariable("session_id") String session_id , @PathVariable("room_identifier") String room_identifier)
	{

		List<UserModel> userList = user_repo.findByName(user) ; 
		if(userList.size() == 0)
			return new Response("Unauthenticated");

		if(userList.get(0).getSession_id() == null)
			return new Response("Logged Out");

		if (!userList.get(0).getSession_id().equals(session_id))
			return new Response("Invalid_Session");

		String arr[] = room_identifier.split("@");
		List<RoomModel>  rooms = room_repo.findRoom(arr[1] , arr[0]);
		if(rooms.size() == 0)
			return new Response("No such room exists");
		else{
			rooms.get(0).setUser(userList.get(0));
			room_repo.save(rooms.get(0));
			return new Response(user + " : Joined Room");
		}
	}


	@GetMapping(value = "/leave/{user}/{session_id}/{room_identifier}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object leave_room(@PathVariable("user") String user , @PathVariable("session_id") String session_id , @PathVariable("room_identifier") String room_identifier)
	{

		List<UserModel> userList = user_repo.findByName(user) ; 
		if(userList.size() == 0)
			return new Response("Unauthenticated");

		if(userList.get(0).getSession_id() == null)
			return new Response("Logged Out");

		if (!userList.get(0).getSession_id().equals(session_id))
			return new Response("Invalid_Session");

		String arr[] = room_identifier.split("@");
		List<RoomModel>  rooms = room_repo.findRoom(arr[1] , arr[0]);

		if(rooms.size() == 0)
			return new Response("No such room exists");
		else{

			 boolean found=false;
			 for(UserModel User : rooms.get(0).getUser())
			 {
				if(User.getName() == userList.get(0).getName())
					found = true ; 
			 }


			if(found){
			 rooms.get(0).removeUser(userList.get(0));
			 room_repo.save(rooms.get(0));
			 return new Response("Succesfully Left Room");
			}
			else
			return new Response("Not in Room");



		}

	}



	@GetMapping(value = "/delete/{user}/{session_id}/{room_identifier}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object delete_room(@PathVariable("user") String user , @PathVariable("session_id") String session_id , @PathVariable("room_identifier") String room_identifier)
	{

		List<UserModel> userList = user_repo.findByName(user) ; 
		if(userList.size() == 0)
			return new Response("Unauthenticated");

		if(userList.get(0).getSession_id() == null)
			return new Response("Logged Out");

		if (!userList.get(0).getSession_id().equals(session_id))
			return new Response("Invalid_Session");

		String arr[] = room_identifier.split("@");
		List<RoomModel>  rooms = room_repo.findRoom(arr[1] , arr[0]);

		if(rooms.size() == 0)
			return new Response("No such room exists");
		else{

				if(rooms.get(0).getCreated_by().equals(user))
				{
					room_repo.deleteById(rooms.get(0).getId());
					return new Response("Succesfully Deleted Room");
				}


				return new Response("Only Admin can delete Room");



			}

	}



	@GetMapping(value = "/get_room_info/{user}/{session_id}/{room_identifier}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object get_room_info(@PathVariable("user") String user , @PathVariable("session_id") String session_id , @PathVariable("room_identifier") String room_identifier)
	{

		List<UserModel> userList = user_repo.findByName(user) ; 
		if(userList.size() == 0)
			return new Response("Unauthenticated");

		if(userList.get(0).getSession_id() == null)
			return new Response("Logged Out");

		if (!userList.get(0).getSession_id().equals(session_id))
			return new Response("Invalid_Session");

		String arr[] = room_identifier.split("@");
		List<RoomModel>  rooms = room_repo.findRoom(arr[1] , arr[0]);

		if(rooms.size() == 0)
			return new Response("No such room exists");
		else{

					String response = "ROOM-NAME : " + rooms.get(0).getName() + "\n" + "ADMIN : " + rooms.get(0).getCreated_by() + "\n" + "DATE OF CREATION : " + rooms.get(0).getCreated_on() + "\n" ;
					List<UserModel> All_Members = rooms.get(0).getUser();
					
					response = response + "Members " + "\n" ;
					for(UserModel User : All_Members){
						response = response + User.getName() + "\n";

					}

					System.out.print(response);
					return new Response(response);

			}

	}


}


