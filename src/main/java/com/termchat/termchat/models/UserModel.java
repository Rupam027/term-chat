package com.termchat.termchat.models ;

import javax.persistence.*;

import com.termchat.termchat.models.SeenModel ; 
import com.termchat.termchat.models.ChatModel ; 
import com.termchat.termchat.models.RoomModel ; 
import java.util.* ; 





@Entity
@Table(name="Chatters")
@SequenceGenerator(name="chatters_chatter_id_seq" , initialValue=1 , allocationSize = 1 , sequenceName="chatters_chatter_id_seq")
public class UserModel{

		
		@Id 
		@GeneratedValue(strategy=GenerationType.SEQUENCE , generator = "chatters_chatter_id_seq")
		@Column(name="chatter_id")
		private long id ; 

		@Column(name="name")
		private String name ;

		@Column(name="password") 
		private String password ;

		@ManyToMany 
		@JoinColumn(name = "rooms" , nullable = true)
		private List<RoomModel> room ;

		@OneToMany
		@Column(name="chat" , nullable = true) 
		private List<ChatModel> chat;

		@OneToMany
		@Column(name="seen" , nullable = true)
		private List<SeenModel> seen;




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
			this.room.add(room);
		}

		public List<RoomModel> getRoom(){
			return this.room ; 

		}


		public void setChat(ChatModel chat){
			this.chat.add(chat) ;
		}

		public List<ChatModel> getChat(){
			return this.chat ; 

		}

		public void setSeen(SeenModel seen){
			this.seen.add(seen) ;
		}

        public List<SeenModel> getSeen(){
			return this.seen ; 

		}
}