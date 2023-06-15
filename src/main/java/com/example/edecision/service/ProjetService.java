package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> ajoutProjet(Projet unProjet) {
		ResponseEntity<String> reponse = this.projetRepo.ajoutProjet(unProjet);
		return reponse;
	}
	//Suppression d'un projet
	public ResponseEntity<String> deleteProjet(int id) {
		ResponseEntity<String> reponse = this.projetRepo.deleteProjet(id);
		return reponse;
	}
	
	//Mise à jour de l'état d'un projet
	public ResponseEntity<String> updateEtat(int id) {
		ResponseEntity<String> reponse = this.projetRepo.updateEtat(id);
		return reponse;
	}

}
