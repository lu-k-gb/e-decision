package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edecision.model.EquipeRepository;
import com.example.edecision.model.ProjetRepository;
import com.example.edecision.model.Proposition;
import com.example.edecision.model.PropositionComplex;
import com.example.edecision.model.PropositionRepository;
import com.example.edecision.model.PropositionSample;
import com.example.edecision.model.UtilisateurRepository;

@Service
public class PropositionService {
	
	@Autowired
	private PropositionRepository propositionRepo;
	@Autowired
	private UtilisateurRepository userRepo;
	@Autowired
	private EquipeRepository equipeRepo;

	public List<Object> listPropositions() {
		List<Object> lesPropositions = this.propositionRepo.listPropositions();
		return lesPropositions;
	}
	public String createProposition(PropositionSample uneProposition)
    {
        //Verification que le ou les porteurs de la proposition existe
          for (int unIdentifiant : uneProposition.getListePorteur()) {
        	  if (userRepo.getutilisateurById(unIdentifiant) == null)
                {
                    return "Erreur l'utilisateur " + unIdentifiant + " n'existe pas ! (code porteur)";
                }
		}
        //Verification que le ou les soutiens de la proposition existe si il y en a
          if ( uneProposition.getListeSoutien() != null)
          {
          for (int unIdentifiant : uneProposition.getListeSoutien()) {
        	  if (userRepo.getutilisateurById(unIdentifiant) == null)
                {
                    return "Erreur l'utilisateur " + unIdentifiant + " n'existe pas ! (code soutien)";
                }
		}
          }
        //Verification que le ou les projets impactés par la proposition existe
          for (int unIdentifiant : uneProposition.getListeImpactees()) {
        	  if (equipeRepo.getEquipeById(unIdentifiant) == null)
                {
                    return "Erreur l'équipe " + unIdentifiant + " n'existe pas ! (code impactees)";
                }
		}
        return this.propositionRepo.createProposition(uneProposition);
    }
	public String createPropositionEscaladeOuAmendement(PropositionComplex uneProposition)
    {
		//Verification que le ou les porteurs de la proposition existe
        for (int unIdentifiant : uneProposition.getListePorteur()) {
      	  if (userRepo.getutilisateurById(unIdentifiant) == null)
              {
                  return "Erreur l'utilisateur " + unIdentifiant + " n'existe pas ! (code porteur)";
              }
		}
      //Verification que le ou les soutiens de la proposition existe si il y en a
        if ( uneProposition.getListeSoutien() != null)
        {
        for (int unIdentifiant : uneProposition.getListeSoutien()) {
      	  if (userRepo.getutilisateurById(unIdentifiant) == null)
              {
                  return "Erreur l'utilisateur " + unIdentifiant + " n'existe pas ! (code soutien)";
              }
		}
        }
      //Verification que le ou les projets impactés par la proposition existe
        for (int unIdentifiant : uneProposition.getListeImpactees()) {
      	  if (equipeRepo.getEquipeById(unIdentifiant) == null)
              {
                  return "Erreur l'équipe " + unIdentifiant + " n'existe pas ! (code impactees)";
              }
		}
        return this.propositionRepo.createPropositionEscaladeOuAmendement(uneProposition);
    }
	
	public String retirerProposition(int id) {
		String reponse = this.propositionRepo.retirerProposition(id);
		return reponse;
	}

}
