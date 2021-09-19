package com.termchat.termchat.models ;   

import javax.persistence.* ;

import com.termchat.termchat.models.UserModel ; 
import com.termchat.termchat.models.ChatModel ; 




@Entity
@Table(name="seen")
public class SeenModel{

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long id ; 

		@ManyToOne
		@JoinColumn(name = "user_id")  
	 	private UserModel user ; 

	 	@ManyToOne
	 	@JoinColumn(name = "chat_id")
		private ChatModel chat;
	
		public void setId(long id){
			this.id = id ;

		}
		
		
		public long  getId(){
			return this.id ;
		}

		public void setUser(UserModel user){
			this.user = user ;
		}

		public UserModel getUser(){
			return this.user ; 

		}

		public void setChat(ChatModel chat){
			this.chat = chat ;
		}

		public ChatModel getChat(){
			return this.chat ; 

		}

		
}