package com.example.edecision.model;


public class AuthentUtilisateur {

	private int id;
	
	private String name;

	private String password;
	
	private String nom;
	
	private String prenom;
	
	private String adresse_mail;
	
	private int id_authentification;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresseMail() {
		return adresse_mail;
	}
	public void setAdresseMail(String adresse_mail) {
		this.adresse_mail = adresse_mail;
	}
	public int getIdAuthentification() {
		return id_authentification;
	}
	public void setIdAuthentification(int id_authentification) {
		this.id_authentification = id_authentification;
	}
	
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
