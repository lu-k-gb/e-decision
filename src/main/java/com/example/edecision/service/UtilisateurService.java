package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.edecision.model.UtilisateurRepository;
import com.example.edecision.model.VoteRepository;
import com.example.edecision.model.Entity.Utilisateur;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository UtilisateurRepo;
	
	public List<Utilisateur> listUtilisateurs() {
		List<Utilisateur> lesUtilisateurs = this.UtilisateurRepo.listUtilisateurs();
		return lesUtilisateurs;
	}
	
	public ResponseEntity<String> ajoutUtilisateur(Utilisateur unUtilisateur) {
		ResponseEntity<String> reponse = this.UtilisateurRepo.ajoutUtilisateur(unUtilisateur);
		return reponse;
	}
	
	public ResponseEntity<String> deleteUtilisateur(int id) {
		ResponseEntity<String> reponse = this.UtilisateurRepo.deleteUtilisateur(id);
		return reponse;
	}
	
	public int getIdUtilisateurByIdAuthent(int idAuthent) {
		int reponse = this.UtilisateurRepo.getIdUtilisateurByIdAuthent(idAuthent);
		return reponse;
	}

}
