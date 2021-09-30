package com.termchat.termchat.models ;


import javax.persistence.* ;
import java.util.* ; 


import com.termchat.termchat.models.ChatModel ; 
import com.termchat.termchat.models.RoomModel ; 
import com.termchat.termchat.models.UserModel ; 




@Entity
@Table(name="Chat")
@SequenceGenerator(name ="chat_id_seq" , initialValue=1 , allocationSize = 1 , sequenceName="chat_id_seq")
public class ChatModel{

		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE , generator="chat_id_seq" )
		@Column(name="id")
		private long id ;   
		private String message ; 

		@ManyToOne
		@JoinColumn(name = "room" , referencedColumnName="id")
		private RoomModel room ; 

		@ManyToOne
		@JoinColumn(name = "chatters" , referencedColumnName="chatter_id")
		private UserModel user;

		

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

		
}