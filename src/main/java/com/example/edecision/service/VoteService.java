package com.example.edecision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edecision.model.VoteRepository;

@Service
public class VoteService {
	
	@Autowired
	private VoteRepository VoteRepo;
	
	
	// *********** EN COURS *********
	public List<Object> listVotes() {
		List<Object> lesVotes = this.VoteRepo.listVotes();
		return lesVotes;
	}

}
