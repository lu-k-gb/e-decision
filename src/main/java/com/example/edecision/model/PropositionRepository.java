package com.example.edecision.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.edecision.model.Entity.Proposition;
import com.example.edecision.model.Entity.PropositionComplex;
import com.example.edecision.model.Entity.PropositionSample;

@Transactional
@Repository
public class PropositionRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	//Récupération de toutes les propositions par utilisation du micro service proposition
	public List<Proposition> listPropositions()
	{
		String uri = "http://127.0.0.1:8082/propositions";
		RestTemplate restTemplate = new RestTemplate();
		Proposition[] lesPropositions = restTemplate.getForObject(uri, Proposition[].class);
		return Arrays.asList(lesPropositions);
		
	}
	//Création d'une proposition simple (originelle)
	public String createProposition(PropositionSample uneProposition)
    {
        String uri = "http://127.0.0.1:8082/proposition";
        RestTemplate restTemplate = new RestTemplateBuilder().interceptors((request, body, execution) -> {
            System.out.print(request.getURI());
            System.out.print(body);
            ClientHttpResponse response = execution.execute(request, body);
            System.out.print(response.getStatusCode());
            return response;
		}).build();
        //Transformation de la proposition simple en une proposition normale
        Proposition laProposition = new Proposition();
        laProposition.setEnonce(uneProposition.getEnonce());
        laProposition.setBlockProject(uneProposition.getBlockProject());
        laProposition.setListeImpactees(uneProposition.getListeImpactees());
        laProposition.setListePorteur(uneProposition.getListePorteur());
        laProposition.setListeSoutien(uneProposition.getListeSoutien());
        laProposition.setNbrSemaineDebat(uneProposition.getNbrSemaineDebat());
        laProposition.setProblematique(uneProposition.getProblematique());
        laProposition.setResolution(uneProposition.getResolution());
    ResponseEntity<String> resultat = restTemplate
    .postForEntity(uri , laProposition, String.class);
        return resultat.getBody().toString();

    }
	
	//Création d'une proposition d'escalade
	public String createPropositionEscaladeOuAmendement(PropositionComplex uneProposition)
    {
        String uri = "http://127.0.0.1:8082/proposition";
        RestTemplate restTemplate = new RestTemplateBuilder().interceptors((request, body, execution) -> {
            System.out.print(request.getURI());
            System.out.print(body);
            ClientHttpResponse response = execution.execute(request, body);
            System.out.print(response.getStatusCode());
            return response;
		}).build();
        //On transforme la proposition d'escalade (complexe) en une proposition normale
        Proposition laProposition = new Proposition();
        laProposition.setEnonce(uneProposition.getEnonce());
        laProposition.setBlockProject(uneProposition.getBlockProject());
        laProposition.setListeImpactees(uneProposition.getListeImpactees());
        laProposition.setListePorteur(uneProposition.getListePorteur());
        laProposition.setListeSoutien(uneProposition.getListeSoutien());
        laProposition.setNbrSemaineDebat(uneProposition.getNbrSemaineDebat());
        laProposition.setProblematique(uneProposition.getProblematique());
        laProposition.setResolution(uneProposition.getResolution());
        //On verifie cependant que la proposition que l'on tente d'escaladée existe
        Proposition propositionImpactee = getPropositionById(uneProposition.getProposal());
        if (propositionImpactee == null)
        {
        	return "Erreur la proposition " + uneProposition.getProposal() + " n'existe pas !"; 
        }
        laProposition.setProposal(propositionImpactee);
    ResponseEntity<String> resultat = restTemplate
    .postForEntity(uri , laProposition, String.class);
        return resultat.getBody().toString();

    }
	
	//On récupére la proposition par rapport à son id
	public Proposition getPropositionById(int idProposition)
	{
		String uri = "http://127.0.0.1:8082/proposition/";
		RestTemplate restTemplate = new RestTemplateBuilder().interceptors((request, body, execution) -> {
            System.out.print(request.getURI());
            System.out.print(body);
            ClientHttpResponse response = execution.execute(request, body);
            System.out.print(response.getStatusCode());
            return response;
		}).build();
		Proposition uneProposition = restTemplate.getForObject(uri + idProposition, Proposition.class);
		return uneProposition;
		
	}
	
	//Suppression (retirage) d'une proposition
	public String retirerProposition(int id)
	{
		String uri = "http://127.0.0.1:8082/proposition/";
		RestTemplate restTemplate = new RestTemplate();
		String test = "";
		ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.DELETE,new HttpEntity<String>(test ), String.class);
		return resultat.getBody();
	}
	
	

}
