package com.example.edecision.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String firstPage() {
		return "Hello World";
	}

}
