package com.example.edecision.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Transactional
@Repository
public class EquipeRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Object> listEquipes()
	{
		String uri = "http://127.0.0.1:8083/equipes";
		RestTemplate restTemplate = new RestTemplate();
		Object[] lesEquipes = restTemplate.getForObject(uri, Object[].class);
		return Arrays.asList(lesEquipes);
		
	}
	
	public String createEquipe(EquipeSimple uneEquipe)
	{
		String uri = "http://127.0.0.1:8083/equipe";
		RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> resultat = restTemplate
	.postForEntity(uri , uneEquipe, String.class);
//		Object[] lesUtilisateurs = restTemplate.getForObject(uri, Object[].class);
		return resultat.getBody().toString();
		
	}
	
	public String delete(int id)
	{
		String uri = "http://127.0.0.1:8083/equipe/";
		RestTemplate restTemplate = new RestTemplate();
	String test = "";
	ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.DELETE,new HttpEntity<String>(test ), String.class);
	 //restTemplate
	//.delete();
//		Object[] lesUtilisateurs = restTemplate.getForObject(uri, Object[].class);
		return "Suppression de l'équipe effectué ";
		
	}
	
	public String addEquipier(int id, List<Integer> lesCoequipiers)
	{
		String uri = "http://127.0.0.1:8083/equipe/";
		RestTemplate restTemplate = new RestTemplateBuilder().interceptors((request, body, execution) -> {
            System.out.print(request.getURI());
            ClientHttpResponse response = execution.execute(request, body);
            System.out.print(response.getStatusCode());
            return response;
		}).build();
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Integer>> requestEntity = new HttpEntity<List<Integer>>(lesCoequipiers, headers);
		ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.PUT,requestEntity, String.class);
		return "Ajout des équipiers ok ";
		
	}
	
	public Equipe getEquipeById(int idEquipe)
	{
		String uri = "http://127.0.0.1:8083/equipe/";
		RestTemplate restTemplate = new RestTemplateBuilder().interceptors((request, body, execution) -> {
            System.out.print(request.getURI());
            System.out.print(body);
            ClientHttpResponse response = execution.execute(request, body);
            System.out.print(response.getStatusCode());
            return response;
		}).build();
		Equipe uneEquipe = restTemplate.getForObject(uri + idEquipe, Equipe.class);
		return uneEquipe;
		
	}

}
