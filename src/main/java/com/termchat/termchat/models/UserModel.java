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
		private long id ;   
		private String name ; 
		private String password ; 
		private RoomModel room ; 
		private ChatModel chat;
		private SeenModel seen;



		public void setId(long id){
			this.id = id ;

		}
		
		@Column(name="id")
		public long  getId(){
			return this.id ;
		}

		public void setName(String name){
			this.name = name ;
		}

		@Column(name="name")
		public String getName(){
			return this.name ; 

		}

		public void setPassword(String password){
			this.password = password ;
		}

		@Column(name="password")
		public String getPassword(){
			return this.password ; 

		}

		public void setRoom(RoomModel room){
			this.room = room ;
		}

		@Column(name="room" , nullable=true)
		public RoomModel getRoom(){
			return this.room ; 

		}


		public void setChat(ChatModel chat){
			this.chat = chat ;
		}

		@Column(name="chat" , nullable=true)
		public ChatModel getChat(){
			return this.chat ; 

		}

		public void setSeen(SeenModel seen){
			this.seen = seen ;
		}

		@Column(name="seen" , nullable=true)
        public SeenModel getSeen(){
			return this.seen ; 

		}
}