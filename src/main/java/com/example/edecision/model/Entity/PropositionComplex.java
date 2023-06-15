package com.example.edecision.model.Entity;

import java.util.List;

//Meme champs que pour une proposition cependant, on spécifie un numéro de proposition au lieu
//d'une proposition directement (utilisé pour la création d'un amendement ou d'une escalade)
public class PropositionComplex {
	private int id;

    private String enonce;

    private String problematique;

    private String resolution;
    private Boolean block_project;


    private int nbr_semaine_debat;

    private List<Integer> lesPorteurs;

    private List<Integer> lesSoutiens;

    private List<Integer> lesImpactees;


    private int proposal;

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
    public int getProposal() {
        return proposal;
    }

    public void setProposal(int proposal) {
        this.proposal = proposal;
    }

}
