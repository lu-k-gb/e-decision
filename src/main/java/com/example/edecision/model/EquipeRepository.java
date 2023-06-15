package com.example.edecision.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.edecision.model.Entity.Equipe;
import com.example.edecision.model.Entity.EquipeSimple;

@Transactional
@Repository
public class EquipeRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	//Récupération de la liste des équipes via l'appel au microservice équipe
	public List<Equipe> listEquipes()
	{
		String uri = "http://127.0.0.1:8083/equipes";
		RestTemplate restTemplate = new RestTemplate();
		Equipe[] lesEquipes = restTemplate.getForObject(uri, Equipe[].class);
		return Arrays.asList(lesEquipes);
		
	}
	//Création d'une équipe via l'utilisation du microservice equipe (On lui passe une equipe sans membre car à la création le seul membre est le team master)
	public ResponseEntity<String> createEquipe(EquipeSimple uneEquipe)
	{
		String uri = "http://127.0.0.1:8083/equipe";
		RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> resultat = restTemplate
	.postForEntity(uri , uneEquipe, String.class);
	if (resultat.getStatusCodeValue() != 200)
	{
		return new ResponseEntity<>("Erreur lors de la création de l'équipe", HttpStatus.BAD_REQUEST);
		//return "Erreur lors de la création de l'équipe";
	}
		return resultat;
		
	}
	
	//Suppression d'une équipe via l'utilisation du microservice équipe
	public ResponseEntity<String> delete(int id)
	{
		String uri = "http://127.0.0.1:8083/equipe/";
		RestTemplate restTemplate = new RestTemplate();
	String test = "";
	ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.DELETE,new HttpEntity<String>(test ), String.class);
		if (resultat.getStatusCodeValue() != 200)
		{
			return new ResponseEntity<>("Erreur lors de la suppression de l'équipe", HttpStatus.BAD_REQUEST);
			//return "Erreur lors de la suppression";
		}
		return resultat;
		//return "Suppression de l'équipe effectuée";
	}
	
	//Ajout des coéquipiers via l'utilisation du microservice equipe
	public ResponseEntity<String> addEquipier(int id, List<Integer> lesCoequipiers)
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
		if (resultat.getStatusCodeValue() != 200)
		{
			return new ResponseEntity<>("Erreur lors de l'ajout des coéquipiers", HttpStatus.BAD_REQUEST);
			//return "Erreur lors d'ajout des coéquipiers";
		}
		return resultat;
		//return "Ajout des équipiers ok ";
		
	}
	
	//Récupération d'une équipe en fonction de son id à l'aide du microservice equipe
	public Equipe getEquipeById(int idEquipe)
	{
		String uri = "http://127.0.0.1:8083/equipe/";
		//Ce RestTemplate a pour vocation l'aide au debug des appels au microservice.
		//Il aurait du être utilisé dans tous les appels mais par manque de temps, ce n'est pas possible.
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
