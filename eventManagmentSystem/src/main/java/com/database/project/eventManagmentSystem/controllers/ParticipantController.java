package com.database.project.eventManagmentSystem.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ParticipantController {
	
	@RequestMapping("/")
	public String showParticipants(HttpSession session) {
		session.setAttribute("name", "Afan");
		return "home";
	}
}
