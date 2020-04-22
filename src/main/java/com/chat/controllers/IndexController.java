package com.chat.controllers;
	
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.messaging.simp.user.SimpUserRegistry;


@Controller
public class IndexController{

	@Autowired
	SimpUserRegistry simpUserRegistry;

	@RequestMapping("/")
	public String index()
	{

		System.out.printf("\n\n\n%d\n\n\n", simpUserRegistry.getUserCount());
		return "index";
	}

	
	
	
}
