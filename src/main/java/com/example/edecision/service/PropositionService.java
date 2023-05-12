package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edecision.model.PropositionRepository;

@Service
public class PropositionService {
	
	@Autowired
	private PropositionRepository propositionRepo;
	
	public List<Object> listPropositions() {
		List<Object> lesPropositions = this.propositionRepo.listPropositions();
		return lesPropositions;
	}

}
