package com.chat.controllers;
	
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.chat.data.services.UserService;
import com.chat.data.services.UserDBMessageService;
import com.chat.data.models.User;
import com.chat.data.models.UserDBMessage;

@Controller
public class MainController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDBMessageService userDBMessageService; 
	
	@RequestMapping("/")
	public String index(Model model, Authentication authentication)
	{

		if(authentication == null || authentication.getName().equals("anonymousUser")) {
			return "redirect:/login";
		}

		List<User> users = userService.findAll(); 
		model.addAttribute("users", users);
		model.addAttribute("info", "this is info");
		return "index";
	}


	@RequestMapping("/private/{username}")
	public String privateChat (Model model,
				   @PathVariable(value= "username") String username,
				   Authentication authentication)
	{
		
		
		if(authentication == null || authentication.getName().equals("anonymousUser")) {
			return "redirect:/login";
		}
		
		User user= userService.findByUsername(username).get(0);
		

		model.addAttribute("target", user);
		return "private";
	}


	@RequestMapping("/public-messages")
	public ResponseEntity<List<UserDBMessage>> getPublicMessages(@RequestParam(value= "page", required=false) Integer page){

		Integer pageNumber= page != null? page : 0;
		int pageSize= 500;
		
		Page<UserDBMessage> pagedResults= userDBMessageService
			.findPublic(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "message.createdAt")));

		List<UserDBMessage> listResults = (List<UserDBMessage>)pagedResults.getContent();
		return new ResponseEntity<List<UserDBMessage>>(listResults, HttpStatus.OK);
		
	} 

	@RequestMapping("/private-messages/{targetUsername}")
	public ResponseEntity<List<UserDBMessage>> getPrivateMessages(
								      @PathVariable("targetUsername") String targetUsername,
								      @RequestParam(value= "page", required=false) Integer page,
								      Authentication authentication)
	{
		Integer pageNumber= page != null? page : 0;
		int pageSize= 500;
		String senderUsername= authentication.getName();

		Page<UserDBMessage> pagedResults= userDBMessageService
			.findMutual(senderUsername,
				    targetUsername,
				    PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "message.createdAt")));

		List<UserDBMessage> listResults = (List<UserDBMessage>)pagedResults.getContent();
		return new ResponseEntity<List<UserDBMessage>>(listResults, HttpStatus.OK);
	} 

}
 
