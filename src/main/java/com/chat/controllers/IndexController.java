package com.chat.controllers;
	
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;


@Controller
public class IndexController{
	
	
	@RequestMapping("/")
	public String index()
	{

		Authentication authentication=
			SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null && !authentication.getName().equals("anonymousUser")) {
			return "index";
		}
		
		return "redirect:login";
	}
	
}
 
