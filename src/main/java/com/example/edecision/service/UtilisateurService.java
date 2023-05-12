package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edecision.model.UtilisateurRepository;
import com.example.edecision.model.VoteRepository;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository UtilisateurRepo;
	
	public List<Object> listUtilisateurs() {
		List<Object> lesUtilisateurs = this.UtilisateurRepo.listUtilisateurs();
		return lesUtilisateurs;
	}

}
