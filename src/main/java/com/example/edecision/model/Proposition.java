package com.example.edecision.model;

import java.time.Instant;
import java.util.Date;
import java.util.List;


public class Proposition {
	private int id;

    private String enonce;

    private String problematique;

    private String resolution;
    private Boolean block_project;

    private Date date_proposition = Date.from(Instant.now());

    private Boolean accepted = false;

    private int nbr_semaine_debat;

    private List<Integer> lesPorteurs;

    private List<Integer> lesSoutiens;

    private List<Integer> lesImpactees;


    private Proposition proposal = null;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEnonce() {
        return enonce;
    }
    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }
    public String getProblematique() {
        return problematique;
    }
    public void setProblematique(String problematique) {
        this.problematique = problematique;
    }
    public String getResolution() {
        return resolution;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    public Boolean getBlockProject() {
        return block_project;
    }
    public void setBlockProject(Boolean block_project) {
        this.block_project = block_project;
    }
    public Date getDateProposition() {
        return date_proposition;
    }
    public void setDateProposition(Date date_proposition) {
        this.date_proposition = date_proposition;
    }
    public Boolean getAccepted() {
        return accepted;
    }
    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
    public int getNbrSemaineDebat() {
        return nbr_semaine_debat;
    }
    public void setNbrSemaineDebat(int nbr_semaine_debat) {
        this.nbr_semaine_debat = nbr_semaine_debat;
    }
    public List<Integer> getListePorteur() {
        return lesPorteurs;
    }

    public void setListePorteur(List<Integer> lesPorteurs) {
        this.lesPorteurs = lesPorteurs;
    }
    public List<Integer> getListeSoutien() {
        return lesSoutiens;
    }

    public void setListeSoutien(List<Integer> lesSoutiens) {
        this.lesSoutiens = lesSoutiens;
    }
    public List<Integer> getListeImpactees() {
        return lesImpactees;
    }

    public void setListeImpactees(List<Integer> lesImpactees) {
        this.lesImpactees = lesImpactees;
    }
    public Proposition getProposal() {
        return proposal;
    }

    public void setProposal(Proposition proposal) {
        this.proposal = proposal;
    }
}
