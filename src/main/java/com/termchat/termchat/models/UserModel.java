package com.termchat.termchat.models ;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.termchat.termchat.models.SeenModel ; 
import com.termchat.termchat.models.ChatModel ; 
import com.termchat.termchat.models.RoomModel ; 






@Entity
@Table(name="user")
public class UserModel{

		
		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="id")
		private long id ; 

		@Column(name="name")
		private String name ;

		@Column(name="password") 
		private String password ;

		@Column(name="room") 
		private RoomModel room ;

		@Column(name="chat") 
		private ChatModel chat;

		@Column(name="seen")
		private SeenModel seen;




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

		public void setPassword(String password){
			this.password = password ;
		}

		public String getPassword(){
			return this.password ; 

		}

		public void setRoom(RoomModel room){
			this.room = room ;
		}

		public RoomModel getRoom(){
			return this.room ; 

		}


		public void setChat(ChatModel chat){
			this.chat = chat ;
		}

		public ChatModel getChat(){
			return this.chat ; 

		}

		public void setSeen(SeenModel seen){
			this.seen = seen ;
		}

        public SeenModel getSeen(){
			return this.seen ; 

		}
}