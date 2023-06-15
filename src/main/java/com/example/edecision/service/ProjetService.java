package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edecision.model.*;
import com.example.edecision.model.Entity.Projet;

@Service
public class ProjetService {
	
	@Autowired
	private ProjetRepository projetRepo;
	
	//Récupération de la liste des projets
	public List<Projet> listProjets() {
		List<Projet> lesProjets = this.projetRepo.listProjets();
		return lesProjets;
	}
	//Création d'un projet
	public String ajoutProjet(Projet unProjet) {
		String reponse = this.projetRepo.ajoutProjet(unProjet);
		return reponse;
	}
	//Suppression d'un projet
	public String deleteProjet(int id) {
		String reponse = this.projetRepo.deleteProjet(id);
		return reponse;
	}
	
	//Mise à jour de l'état d'un projet
	public String updateEtat(int id) {
		String reponse = this.projetRepo.updateEtat(id);
		return reponse;
	}

}
