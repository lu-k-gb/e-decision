package com.example.edecision.model; 



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.edecision.model.Entity.Authentification;


@Transactional()
@Repository
public class AuthentificationRepository {

	@PersistenceContext
	private EntityManager em;
	

	
	private PasswordEncoder passwordEncoder;
	
	//Récupération d'authentification de l'utilisateur en fonction de son login
	public Authentification getUserByName(String name)
	{
		Authentification entity;
		try {
		 entity = em.createQuery("SELECT p FROM Authentifications p WHERE p.name = :name", Authentification.class).setParameter("name", name).getSingleResult();
		}
		catch (Exception e)
		{
			entity = null;
		}
		return entity;
	}
	
	//Création d'une Authentification
		public ResponseEntity<String> createAuthentification(Authentification uneAuthentification)
		{
			int dernierId = 0;
			//On cherche premièrement que l'id de l'objet n'existe pas deja
			Authentification entity = em.find(Authentification.class, uneAuthentification.getId());
			if (entity != null) {
				return new ResponseEntity<>("Authentification deja existante", HttpStatus.UNAUTHORIZED);
			}
			else {
				//On vérifie ensuite que le login n'est pas deja utilisé
				 entity = getUserByName(uneAuthentification.getName());
				
			 if (entity != null)
			 {
				 return new ResponseEntity<>("Un utilisateur utilise deja ce login", HttpStatus.UNAUTHORIZED);
			 }
			 else
			 {
				 //On crypte le mot de passe de tel sorte qu'il soit decryptable par l'authentification
				setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
					String newPassword = this.passwordEncoder.encode(uneAuthentification.getPassword());
					uneAuthentification.setPassword(newPassword.substring(8));


					em.persist(uneAuthentification);
					//On recupère ensuite l'id de l'enregistrement authentification que l'on vient de créer
					entity = getUserByName(uneAuthentification.getName());
					
					 if (entity != null)
					 {
						 dernierId = entity.getId();
					 }
					 else
					 {
						 //Si on ne le retrouve pas => erreur
						 return new ResponseEntity<>("La création a échoué", HttpStatus.UNAUTHORIZED);
					 }

					return new ResponseEntity<>("Ajout de l'authentification réalisée " + dernierId , HttpStatus.CREATED);

		    }
			}
			
		}
		
		//Suppression d'une authentification
		public ResponseEntity<String> deleteAuthentification(int numero) {
			Authentification entity = em.find(Authentification.class, numero);
			if (entity == null) {
				return new ResponseEntity<>("Numéro inconnu, veuillez réessayez"  , HttpStatus.NOT_FOUND);
			}
			em.remove(entity);
			return new ResponseEntity<>("Suppression de l'authentification réalisée " + entity.getId() , HttpStatus.OK);
		}
	        
		
		// Méthode pour crypté le password
		public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
			Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
			this.passwordEncoder = passwordEncoder;
			
		}
		
}
