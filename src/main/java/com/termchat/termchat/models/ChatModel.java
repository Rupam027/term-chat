package com.termchat.termchat.models ;


import javax.persistence.* ;
import java.util.* ; 


import com.termchat.termchat.models.ChatModel ; 
import com.termchat.termchat.models.RoomModel ; 
import com.termchat.termchat.models.UserModel ; 




@Entity
@Table(name="Chat")
public class ChatModel{

		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long id ;   
		private String message ; 

		@ManyToOne
		@JoinColumn(name = "room_id")
		private RoomModel room ; 

		@ManyToOne
		@JoinColumn(name = "user_id")
		private UserModel user;

		@OneToMany
		@JoinColumn(name = "seen_id")
		private List<SeenModel> seen;
		

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
			this.seen.add(seen) ;
		}

		public List<SeenModel> getSeen(){
			return this.seen ; 

		}
}