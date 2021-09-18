package com.termchat.termchat.models ;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.termchat.termchat.models.UserModel ; 
import com.termchat.termchat.models.ChatModel ; 



@Entity
@Table(name="room")
public class RoomModel{

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long id ;   
		private String name ; 
		private String created_on ; 
		private String created_by ; 
		private ChatModel chat;
		private UserModel user;

		public void setId(long id){
			this.id = id ;

		}
		
		public long  getId(){
			return this.id ; 
		}

		public void setName(String name){
			this.name = name ;
		}

		public String getName(){
			return this.name ; 

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

		public void setCreated_on(String created_on){
			this.created_on = created_on ;
		}

		public String getCreated_on(){
			return this.created_on ; 

		}

		public void setCreated_by(String created_by){
			this.created_by = created_by ;
		}

		public String getCreated_by(){
			return this.created_by ; 

		}
}