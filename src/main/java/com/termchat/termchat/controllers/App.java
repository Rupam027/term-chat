package com.termchat.termchat.controllers; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class App  {

	@GetMapping("/hello")
	public String getHello(){

		return "Hi rupam" ; 
	}
	
}
