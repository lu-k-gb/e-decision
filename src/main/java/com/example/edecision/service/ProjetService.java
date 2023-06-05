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
	
	public String ajoutProjet(Projet unProjet) {
		String reponse = this.projetRepo.ajoutProjet(unProjet);
		return reponse;
	}
	
	public String deleteProjet(int id) {
		String reponse = this.projetRepo.deleteProjet(id);
		return reponse;
	}
	
	public String updateEtat(int id) {
		String reponse = this.projetRepo.updateEtat(id);
		return reponse;
	}

}
