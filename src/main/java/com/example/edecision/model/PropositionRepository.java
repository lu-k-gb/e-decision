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

@Transactional
@Repository
public class PropositionRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Object> listPropositions()
	{
		String uri = "http://127.0.0.1:8082/propositions";
		RestTemplate restTemplate = new RestTemplate();
		Object[] lesPropositions = restTemplate.getForObject(uri, Object[].class);
		return Arrays.asList(lesPropositions);
		
	}
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
//        Object[] lesUtilisateurs = restTemplate.getForObject(uri, Object[].class);
        return resultat.getBody().toString();

    }
	
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
        Proposition laProposition = new Proposition();
        laProposition.setEnonce(uneProposition.getEnonce());
        laProposition.setBlockProject(uneProposition.getBlockProject());
        laProposition.setListeImpactees(uneProposition.getListeImpactees());
        laProposition.setListePorteur(uneProposition.getListePorteur());
        laProposition.setListeSoutien(uneProposition.getListeSoutien());
        laProposition.setNbrSemaineDebat(uneProposition.getNbrSemaineDebat());
        laProposition.setProblematique(uneProposition.getProblematique());
        laProposition.setResolution(uneProposition.getResolution());
        Proposition propositionImpactee = getPropositionById(uneProposition.getProposal());
        if (propositionImpactee == null)
        {
        	return "Erreur la proposition " + uneProposition.getProposal() + " n'existe pas !"; 
        }
        laProposition.setProposal(propositionImpactee);
    ResponseEntity<String> resultat = restTemplate
    .postForEntity(uri , laProposition, String.class);
//        Object[] lesUtilisateurs = restTemplate.getForObject(uri, Object[].class);
        return resultat.getBody().toString();

    }
	
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
	
	public String retirerProposition(int id)
	{
		String uri = "http://127.0.0.1:8082/proposition/";
		RestTemplate restTemplate = new RestTemplate();
		String test = "";
		ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.DELETE,new HttpEntity<String>(test ), String.class);
		return resultat.getBody();
	}
	
	

}
