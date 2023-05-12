package com.example.edecision.model;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;


@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Repository
public class AuthentificationRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired 
    private PlatformTransactionManager transactionManager;
	
	private PasswordEncoder passwordEncoder;
	private TransactionTemplate transactionTemplate;
	
	public Authentification getUserByName(String name)
	{
		Authentification entity = em.createQuery("SELECT p FROM Authentifications p WHERE p.name = :name", Authentification.class).setParameter("name", name).getSingleResult();
		return entity;
	}
	
	//Création d'une Authentification
		public String createAuthentification(Authentification uneAuthentification)
		{
			transactionTemplate = new TransactionTemplate(transactionManager);
			
			String test;
			Authentification entity = em.find(Authentification.class, uneAuthentification.getId());
			if (entity != null) {
				return "Authentification deja existante";
			}
			else {
//				boolean upgradeEncoding =  this.passwordEncoder.upgradeEncoding(uneAuthentification.getPassword());
//				if (upgradeEncoding) {
				setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
					String newPassword = this.passwordEncoder.encode(uneAuthentification.getPassword());
					uneAuthentification.setPassword(newPassword.substring(8));
//				}	
			
			try {
				String test2 = transactionTemplate.execute(transactionStatus -> {
					em.persist(uneAuthentification);
					transactionStatus.flush();
					return "Ajout de l'authentification réalisée";
				});
				test = test2;
			    
				
//				em.persist(uneAuthentification);
//				em.flush();
//				test = "Ajout de l'authentification réalisée";
				throw new SQLIntegrityConstraintViolationException();
				
				

	        } catch (Exception e) {
//	            if(e instanceof SQLIntegrityConstraintViolationException) {
//	                    if(e.getMessage().contains("Duplicate")) {
//	                        if (e.getMessage().contains("name")) {
	                        	test = e.getMessage(); 
	                        
//	                    }
	                
			//catch java.sql.SQLIntegrityConstraintViolationException {
				
//			}
//	            }
	        }
			
		    }
			return test;
//			});
//			return test2;
		}
	        
		
		
		public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
			Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
			this.passwordEncoder = passwordEncoder;
			
		}
		
}
