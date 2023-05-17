package com.example.edecision.model;

import java.io.Console;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;


@Transactional()
@Repository
public class AuthentificationRepository {

	@PersistenceContext
	private EntityManager em;
	

	
	private PasswordEncoder passwordEncoder;
	
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
			Authentification entity = em.find(Authentification.class, uneAuthentification.getId());
			if (entity != null) {
				return new ResponseEntity<>("Authentification deja existante", HttpStatus.UNAUTHORIZED);
				//return "Authentification deja existante";
			}
			else {
				 entity = getUserByName(uneAuthentification.getName());
				
			 if (entity != null)
			 {
				 return new ResponseEntity<>("Un utilisateur utilise deja ce login", HttpStatus.UNAUTHORIZED);
				 //return ;
			 }
			 else
			 {
				setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
					String newPassword = this.passwordEncoder.encode(uneAuthentification.getPassword());
					uneAuthentification.setPassword(newPassword.substring(8));


					em.persist(uneAuthentification);
					entity = getUserByName(uneAuthentification.getName());
					
					 if (entity != null)
					 {
						 dernierId = entity.getId();
					 }
					 else
					 {
						 return new ResponseEntity<>("La création a échoué", HttpStatus.UNAUTHORIZED);
					 }

					return new ResponseEntity<>("Ajout de l'authentification réalisée " + dernierId , HttpStatus.CREATED);
					//return  "Ajout de l'authentification réalisée";

		    }
			}
			
		}
	        
		
		
		public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
			Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
			this.passwordEncoder = passwordEncoder;
			
		}
		
}
