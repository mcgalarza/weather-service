package com.redbee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
	
//	@RequestMapping("/")
//	public String home() {
//		return "Weather Service Code Challenge";
//	}
//	
//	@RequestMapping("/boards")
//	public String boards() {
//		return "boards";
//	}
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
}
