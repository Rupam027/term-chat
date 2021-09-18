package com.termchat.termchat.controllers; 

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired ; 

//import org.springframework.context.annotation.Beans;

import org.springframework.http.MediaType ; 
import com.termchat.termchat.models.* ; 


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

		if(details.getUsername().equals("Rupam") && details.getPassword().equals("rupam2000"))
		return new Response("Authenticated");
		else
		return new Response("Unauthenticated") ; 
	
	}

	@PostMapping(value = "/auth/register" , consumes=MediaType.APPLICATION_JSON_VALUE , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object register(@RequestBody AuthDetails details){
	 	UserModel user = new UserModel() ; 

	 	try{
		
		user.setName(details.getUsername());
		user.setPassword(details.getPassword());
		return repo.save(user);
		}
		catch(Exception e){
			System.out.print(user.getName() + user.getPassword() + user.getRoom() + user.getChat() + user.getSeen());
			return new Response(e.toString());



		}
	}

}