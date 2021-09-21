package com.termchat.termchat.models ;

import javax.persistence.* ; 
import com.termchat.termchat.models.UserModel ; 
import com.termchat.termchat.models.ChatModel ; 
import java.util.* ;


@Entity
@Table(name="Room")
public class RoomModel{

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long id ;   
		private String name ; 
		private String created_on ; 
		private String created_by ; 
		
		@OneToMany
		@Column(name = "chat" , nullable = true)
		private List<ChatModel> chat;


		@ManyToMany 
		@JoinColumn(name = "users" , nullable = true)
		private List<UserModel> user;

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
			this.user.add(user) ;
		}

		public List<UserModel> getUser(){
			return this.user ; 

		}

		public void setChat(ChatModel chat){
			this.chat.add(chat) ;
		}

		public List<ChatModel> getChat(){
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