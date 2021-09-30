package com.termchat.termchat.models ;   

import javax.persistence.* ;

import com.termchat.termchat.models.UserModel ; 
import com.termchat.termchat.models.ChatModel ; 




@Entity
@Table(name="Seen")
@SequenceGenerator(name="seen_id_seq" , initialValue=1 , allocationSize = 1 , sequenceName="seen_id_seq")
public class SeenModel{

		@Id 
		@GeneratedValue(strategy=GenerationType.SEQUENCE , generator="seen_id_seq")
		private long id ; 

		@ManyToOne
		@JoinColumn(name = "chatters" , referencedColumnName="chatter_id") 
		private UserModel user ; 

	 	@ManyToOne
	 	@JoinColumn(name = "chat" , referencedColumnName="id")
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