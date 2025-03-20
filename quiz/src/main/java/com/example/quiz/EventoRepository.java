/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.quiz;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class EventoRepository {


	private final Map<String, Evento> baseDeDatos = new HashMap<>();
	private final Map<String, String> authTokens = new HashMap<>();
	private final Map<String, String> tracking = new HashMap<>();
	
	public Map<String, String> getTrack() {
		return tracking;
	}

	public Evento save(Evento evento) {
		baseDeDatos.put(evento.getId(), evento);
		authTokens.put("token-" + evento.getId(), evento.getId());
		return evento;
	}

	public Persona savePerson(String idEvento, Persona persona) {
		baseDeDatos.get(idEvento).getInvitados().add(persona);
		tracking.put("track-"+baseDeDatos.get(idEvento).getId()+ "-" + 
		persona.getId(), baseDeDatos.get(idEvento).getId() + "-" + 
		persona.getId());
		return persona;
	}

	public Persona findPersonByEvent(String idEvento, String idPersona) {
		Evento evento = baseDeDatos.get(idEvento); 
		List<Persona> invitados = evento.getInvitados();

		for (int i=0; i<invitados.size(); i++){
			if (invitados.get(i).getId().equals(idPersona)){
				return invitados.get(i);
			}
		}
		return null;
	}

	public List<Evento> searchByFilters(LocalDate fecha) {
		return baseDeDatos.values().stream()
				.filter(u -> fecha == null || u.getFecha().equals(fecha))
				.collect(Collectors.toList());
	}

	public Evento findByAuthToken(String authToken) {
		String eventId = authTokens.get("token-"+authToken);
		if (eventId != null) {
			return baseDeDatos.get(eventId);
		}
		return null;
	}

	public void deletePersonByEvent(String idEvento, String idPersona) {

		Persona persona = findPersonByEvent(idEvento,idPersona);

		if(persona != null){
			baseDeDatos.get(idEvento).getInvitados().remove(persona);
		}
	}

	public String findPersonByTrackingId (String idEvento, String trackId) {
		Evento evento = baseDeDatos.get(idEvento);
		if (evento != null) {
			return tracking.get("track-" + idEvento + "-" + trackId);
		}
		return null;
	}



}
