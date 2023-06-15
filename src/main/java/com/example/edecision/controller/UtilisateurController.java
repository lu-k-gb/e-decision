package com.example.edecision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.edecision.model.Entity.AuthentUtilisateur;
import com.example.edecision.model.Entity.Authentification;
import com.example.edecision.model.Entity.Utilisateur;
import com.example.edecision.service.EquipeService;
import com.example.edecision.service.JwtUserDetailsService;
import com.example.edecision.service.UtilisateurService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
//Annotation qui permet de spécifier à swagger que l'on va passer le token que l'on aura saisi pour l'authent swagger
@SecurityRequirement(name = "bearerAuth")
//Toutes les routes de ce controller appellent le micro-service utilisateurs
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateursService;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	//Récupération de tous les utilisateurs
	@GetMapping(value = "/utilisateurs")
	public List<Utilisateur> getUtilisateurs() {
		List<Utilisateur> lesUtilisateurs = utilisateursService.listUtilisateurs(); 
		return lesUtilisateurs;
	}
	
	
	//Suppression d'un utilisateur + authentification
	@DeleteMapping("user/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id)
	{
		ResponseEntity<String> result;
		//Appel de la suppression de l'authentification
	    result = userDetailsService.deleteAuthentification(id);
	    if (result.getStatusCode() == HttpStatus.OK)
		{
	    	//Si authent supprimé alors on récupère l'id qui a été supprimé
			String resultat = result.getBody().substring(43);;
			//On appel la méthode du micro service utilisateur permettant de récupérer l'id utilisateur
			//à partir de son id authentification
			int idUtilisateur = utilisateursService.getIdUtilisateurByIdAuthent(Integer.parseInt(resultat));
			//Une fois cet id récupéré on supprime l'utilisateur
			ResponseEntity<String> test = utilisateursService.deleteUtilisateur(idUtilisateur);
		}
		return result;
	}

}
