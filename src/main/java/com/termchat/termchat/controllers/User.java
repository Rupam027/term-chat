package com.termchat.termchat.controllers; 

import org.springframework.web.bind.annotation.* ;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired ; 


import org.springframework.http.MediaType ; 
import com.termchat.termchat.models.* ; 

import java.util.* ; 



class AuthDetails{

			private String username ; 
			private String password ;

			public AuthDetails(String username , String password){
					this.username = username ;
					this.password = password ;

			}
			

			public String getUsername(){
				return this.username ; 

			}

			public String getPassword(){
				return this.password ; 
			}

			public void setUsername(String username){
				this.username = username ; 

			}

			public void setPassword(String password){
				this.password = password ; 
			}
			
}





@RestController
public class User {

	@Autowired 
	UserRepository repo ; 

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 

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



	


	@PostMapping(value = "/auth/login" ,consumes=MediaType.APPLICATION_JSON_VALUE , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Response login(@RequestBody AuthDetails details){



		List<UserModel> userList = repo.findByName(details.getUsername()) ; 
		
		if(userList.size() == 1){

			String password = userList.get(0).getPassword(); 
			String sid = userList.get(0).getSession_id();
			if(sid != null)
			return new Response("Already Logged In"); 

			if(encoder.matches(details.getPassword() , password)) {
			
			sid = UUID.randomUUID().toString();
			repo.updateSession(sid , details.getUsername());
			return new Response("Authenticated with generated session : " + sid);
			}
			else
			return new Response("Unauthenticated");

		}

		return new Response("Unauthenticated") ; 
	
	}

	@PostMapping(value = "/auth/register" , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object register(@RequestBody AuthDetails details){
	 	UserModel user = new UserModel() ; 
	 	

		

	 	try{
	 	
				
		if(repo.findByName(details.getUsername()).size() == 1)
			return new Response("Username Not Available");

		
		user.setName(details.getUsername());
		user.setPassword(encoder.encode(details.getPassword()));
		return repo.save(user);
		}
		catch(Exception e){
			System.out.print(user.getName() + user.getPassword());
			return new Response(e.toString());



		}
	}


	@GetMapping(value="/auth/logout/{name}/{sid}" , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response logout(@PathVariable("sid") String sid , @PathVariable("name") String name){
		List<UserModel> userList = repo.findByName(name) ; 
		if(userList.size() == 0)
			return new Response("Unauthenticated");
		if(userList.get(0).getSession_id() == null)
			return new Response("Already Logged Out");

		repo.updateSession(null , name);
		return new Response("Logged Out");
	}

	@GetMapping(value="/auth/delete/{name}/{sid}" , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response deleteAccount(@PathVariable("name") String name , @PathVariable("sid") String sid){
		List<UserModel> userList = repo.findByName(name) ; 
		if(userList.size() == 0)
			return new Response("Unauthenticated");

		if(userList.get(0).getSession_id() == null)
			return new Response("Logged Out");

		if(!!userList.get(0).getSession_id().equals(sid))
			return new Response("Unauthenticated");


		repo.deleteByName(name);
		return new Response("Deleted Account");



	}

	
	

}