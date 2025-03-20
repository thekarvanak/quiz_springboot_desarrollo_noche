package com.example.quiz;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {
	private final EventoService eventoService;
	
	@Autowired
	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}
	
	// Crear un nuevo evento
	@PostMapping("/{id}/inscribir")
	public ResponseEntity<Persona> createEvento(
			@RequestBody Persona persona,
			@PathVariable String id,
			@RequestHeader("X-Tracking-Id") String trackId) {
		String existingPersona = eventoService.findPersonByTrackingId(id, trackId);
		if(existingPersona != null) {
			Persona newPersona = eventoService.savePerson(id, persona);
			return new ResponseEntity<>(newPersona, HttpStatus.CREATED);
		} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	// Eliminar api/evento/{id}/inscripcion/{personaId}
	@DeleteMapping("/{eventoId}/inscripcion/{personaId}")
	public ResponseEntity<Void> deletePersonaInEvento(
			@PathVariable String eventoId,
			@PathVariable String personaId,
			@RequestHeader("Authorization") String authToken) {
		Evento evento = eventoService.findByAuthToken(authToken);
		if(evento != null) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
			/*
//			return new ResponseEntity<>(HttpStatus.ACCEPTED);
			Persona existingPersona = eventoService.findPersonByEvent(eventoId, personaId);
			if(existingPersona != null) {
				eventoService.deletePersonByEvent(eventoId, personaId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}*/
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	// parámetros de Query String
	@GetMapping
	public ResponseEntity<List<Evento>> buscarEventos(
			@RequestParam(required = false) LocalDate fecha) {
		List<Evento> eventos = (List<Evento>) eventoService.searchByFilters(fecha);
		return new ResponseEntity<>(eventos, HttpStatus.OK);
	}

	@GetMapping("/track")
	public ResponseEntity<Map<String, String>> getTrack() {
		Map<String, String> track = eventoService.getTrack();
		return new ResponseEntity<>(track, HttpStatus.OK);
	}
	
}