package com.example.edecision.model;

import java.util.List;


public class Equipe {
	private int id;
	private String name;
	private int id_manager;
	private int id_team_master;
	private int id_projet;
	

	private List<Integer> lesParticipants;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIdManager() {
		return id_manager;
	}
	public void setIdManager(int id_manager) {
		this.id_manager = id_manager;
	}
	public int getIdTeamMaster() {
		return id_team_master;
	}
	public void setIdTeamMaster(int id_team_master) {
		this.id_team_master = id_team_master;
	}
	public int getIdProjet() {
		return id_projet;
	}
	public void setIdProjet(int id_projet) {
		this.id_projet = id_projet;
	}
	public List<Integer> getListeEquipier() {
		return lesParticipants;
	}

	public void setListeEquipier(List<Integer> lesParticipants) {
		this.lesParticipants = lesParticipants;
	}
	
}
