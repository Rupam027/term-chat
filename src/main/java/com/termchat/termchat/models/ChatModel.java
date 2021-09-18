package com.termchat.termchat.models ;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.termchat.termchat.models.ChatModel ; 
import com.termchat.termchat.models.RoomModel ; 
import com.termchat.termchat.models.UserModel ; 




@Entity
@Table(name="chat")
public class ChatModel{

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long id ;   
		private String message ; 
		private RoomModel room ; 
		private UserModel user;
		private SeenModel seen;
		public void setId(long id){
			this.id = id ;

		}

		public long  getId(){
			return this.id ;
		}

		public void setMessage(String message){
			this.message = message ;
		}

		public String getMessage(){
			return this.message ; 

		}

		public void setRoom(RoomModel room){
			this.room = room ;
		}

		public RoomModel getRoom(){
			return this.room ; 

		}

		public void setUser(UserModel user){
			this.user = user ;
		}

		public UserModel getUser(){
			return this.user ; 

		}

		public void setSeen(SeenModel seen){
			this.seen = seen ;
		}

		public SeenModel getSeen(){
			return this.seen ; 

		}
}