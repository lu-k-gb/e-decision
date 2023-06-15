package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.edecision.model.EquipeRepository;
import com.example.edecision.model.ProjetRepository;
import com.example.edecision.model.PropositionRepository;
import com.example.edecision.model.UtilisateurRepository;
import com.example.edecision.model.Entity.Proposition;
import com.example.edecision.model.Entity.PropositionComplex;
import com.example.edecision.model.Entity.PropositionSample;

@Service
public class PropositionService {
	
	@Autowired
	private PropositionRepository propositionRepo;
	@Autowired
	private UtilisateurRepository userRepo;
	@Autowired
	private EquipeRepository equipeRepo;

	public List<Proposition> listPropositions() {
		List<Proposition> lesPropositions = this.propositionRepo.listPropositions();
		return lesPropositions;
	}
	//Création de la proposition
	public ResponseEntity<String> createProposition(PropositionSample uneProposition)
    {
        //Verification que le ou les porteurs de la proposition existe
          for (int unIdentifiant : uneProposition.getListePorteur()) {
        	  if (userRepo.getutilisateurById(unIdentifiant) == null)
                {
        		  return new ResponseEntity<>("Erreur l'utilisateur " + unIdentifiant + " n'existe pas ! (code porteur)", HttpStatus.BAD_REQUEST);
                }
		}
        //Verification que le ou les soutiens de la proposition existe si il y en a
          if ( uneProposition.getListeSoutien() != null)
          {
          for (int unIdentifiant : uneProposition.getListeSoutien()) {
        	  if (userRepo.getutilisateurById(unIdentifiant) == null)
                {
        		  return new ResponseEntity<>("Erreur l'utilisateur " + unIdentifiant + " n'existe pas ! (code soutien)", HttpStatus.BAD_REQUEST);
                }
		}
          }
        //Verification que le ou les projets impactés par la proposition existe
          for (int unIdentifiant : uneProposition.getListeImpactees()) {
        	  if (equipeRepo.getEquipeById(unIdentifiant) == null)
                {
        		  return new ResponseEntity<>("Erreur l'équipe " + unIdentifiant + " n'existe pas ! (code impactees)", HttpStatus.BAD_REQUEST);
                }
		}
        return this.propositionRepo.createProposition(uneProposition);
    }
	
	//Création de la proposition d'escalade
	public ResponseEntity<String> createPropositionEscaladeOuAmendement(PropositionComplex uneProposition)
    {
		//Verification que le ou les porteurs de la proposition existe
        for (int unIdentifiant : uneProposition.getListePorteur()) {
      	  if (userRepo.getutilisateurById(unIdentifiant) == null)
              {
      		return new ResponseEntity<>("Erreur l'utilisateur " + unIdentifiant + " n'existe pas ! (code porteur)", HttpStatus.BAD_REQUEST);
              }
		}
      //Verification que le ou les soutiens de la proposition existe si il y en a
        if ( uneProposition.getListeSoutien() != null)
        {
        for (int unIdentifiant : uneProposition.getListeSoutien()) {
      	  if (userRepo.getutilisateurById(unIdentifiant) == null)
              {
      		return new ResponseEntity<>("Erreur l'utilisateur " + unIdentifiant + " n'existe pas ! (code soutien)", HttpStatus.BAD_REQUEST);
              }
		}
        }
      //Verification que le ou les projets impactés par la proposition existe
        for (int unIdentifiant : uneProposition.getListeImpactees()) {
      	  if (equipeRepo.getEquipeById(unIdentifiant) == null)
              {
      		return new ResponseEntity<>("Erreur l'équipe " + unIdentifiant + " n'existe pas ! (code impactees)", HttpStatus.BAD_REQUEST);
              }
		}
        return this.propositionRepo.createPropositionEscaladeOuAmendement(uneProposition);
    }
	
	//Suppression d'une proposition
	public ResponseEntity<String> retirerProposition(int id) {
		ResponseEntity<String> reponse = this.propositionRepo.retirerProposition(id);
		return reponse;
	}

}
