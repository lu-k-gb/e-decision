package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edecision.model.*;

@Service
public class ProjetService {
	
	@Autowired
	private ProjetRepository projetRepo;
	
	public List<Object> listProjets() {
		List<Object> lesProjets = this.projetRepo.listProjets();
		return lesProjets;
	}

}
