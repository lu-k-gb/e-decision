package com.example.edecision.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;



@Repository
public class UtilisateurRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Utilisateur getUserByName(String name)
	{
		Utilisateur entity = em.createQuery("SELECT p FROM Utilisateurs p WHERE p.name = :name", Utilisateur.class).setParameter("name", name).getSingleResult();
		//, PokemonEntity.class).setParameter("type", type)
		//return em.createQuery("SELECT p FROM Pokemon p", PokemonEntity.class).getResultList();
		return entity;
	}
}
