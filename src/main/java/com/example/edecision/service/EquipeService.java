package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edecision.model.EquipeRepository;

@Service
public class EquipeService {
	
	@Autowired
	private EquipeRepository equipeRepo;
	
	public List<Object> listEquipes() {
		List<Object> lesEquipes = this.equipeRepo.listEquipes();
		return lesEquipes;
	}

}
