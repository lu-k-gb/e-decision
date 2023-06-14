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

import com.example.edecision.model.AuthentUtilisateur;
import com.example.edecision.model.Authentification;
import com.example.edecision.model.Utilisateur;
import com.example.edecision.service.EquipeService;
import com.example.edecision.service.JwtUserDetailsService;
import com.example.edecision.service.UtilisateurService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateursService;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@GetMapping(value = "/utilisateurs")
	public List<Object> getUtilisateurs() {
		List<Object> lesUtilisateurs = utilisateursService.listUtilisateurs(); 
		return lesUtilisateurs;
	}
	
	
	
	//Suppression d'un utilisateur + authentification
	@DeleteMapping("user/delete/{numero}")
	public ResponseEntity<String> delete(@PathVariable("numero") int numero)
	{
		ResponseEntity<String> result;
		//Appel de la suppression de l'authentification
	    result = userDetailsService.deleteAuthentification(numero);
	    if (result.getStatusCode() == HttpStatus.OK)
		{
	    	//Si authent supprimé alors on récupère l'id qui a été supprimé
			String resultat = result.getBody().substring(43);
			//unutilisateur.setIdAuthentification(Integer.parseInt(resultat));
			//On appel la méthode du micro service utilisateur permettant de récupérant l'id utilisateur
			//à partir de son id authentification
			int idUtilisateur = utilisateursService.getIdUtilisateurByNumeroAuthent(Integer.parseInt(resultat));
			//Une fois cet id récupéré on supprime l'utilisateur
			String test = utilisateursService.deleteUtilisateur(idUtilisateur);
		}
		return result;
	}

}
