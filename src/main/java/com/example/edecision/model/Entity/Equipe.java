package com.example.edecision.model.Entity;

import java.util.List;


public class Equipe {
	private int id;
	private String name;
	//Id utilisateur du manageur de l'équipe
	private int id_manager;
	//Id utilisateurs du chef de l'équipe
	private int id_team_master;
	//Id du projet auquel l'équipe est rattaché
	private int id_projet;
	
	//Les équipiers de cette équipe
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
