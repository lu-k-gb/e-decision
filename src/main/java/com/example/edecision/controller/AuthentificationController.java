package com.example.edecision.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {
	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
}