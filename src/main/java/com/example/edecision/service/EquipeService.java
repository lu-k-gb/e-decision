package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edecision.model.EquipeRepository;
import com.example.edecision.model.EquipeSimple;
import com.example.edecision.model.ProjetRepository;
import com.example.edecision.model.UtilisateurRepository;

@Service
public class EquipeService {
	
	@Autowired
	private EquipeRepository equipeRepo;
	@Autowired
	private UtilisateurRepository userRepo;
	@Autowired
	private ProjetRepository projetRepo;
	
	public List<Object> listEquipes() {
		List<Object> lesEquipes = this.equipeRepo.listEquipes();
		return lesEquipes;
	}
	
	//Création d'une équipe
			public String createEquipe(EquipeSimple uneEquipe)
			{
				//Verification que le manager est un utilisateur existant, que le team master est un profil existant
				// et que le projet existe
				if (userRepo.getutilisateurById(uneEquipe.getIdManager()) == null)
				{
					return "Erreur l'utilisateur " + uneEquipe.getIdManager() + " n'existe pas ! (code manager)";
				}
				if (userRepo.getutilisateurById(uneEquipe.getIdTeamMaster()) == null)
				{
					return "Erreur l'utilisateur " + uneEquipe.getIdTeamMaster() + " n'existe pas ! (code team master)";
				}
				if (projetRepo.getProjetById(uneEquipe.getIdProjet()) == null)
				{
					return "Erreur le projet " + uneEquipe.getIdProjet() + " n'existe pas ! (code projet)";
				}
				return this.equipeRepo.createEquipe(uneEquipe);
			}
			
			//Suppression d'une equipe
			public String delete(int id) {
				return this.equipeRepo.delete(id);
			}
			
			//Différentes méthodes pour modifier les attributs d'une equipe
			public String addEquipier(int id, List<Integer> lesCoequipiers) {
				//Appel du microservice utilisateur afin de vérifier que les id de la liste existe tous
				for (Integer unCoequipier : lesCoequipiers) {
					if (userRepo.getutilisateurById(unCoequipier) == null)
					{
						return "Erreur l'utilisateur " + unCoequipier + " n'existe pas !";
					}
				}
				return this.equipeRepo.addEquipier(id, lesCoequipiers);
			}

}
