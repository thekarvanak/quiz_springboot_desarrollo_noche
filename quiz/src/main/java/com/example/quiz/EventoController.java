package com.example.quiz;

import java.util.List;

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
	public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
		Evento newEvento = eventoService.save(evento);
		return new ResponseEntity<>(newEvento, HttpStatus.CREATED);
	}
	
	
	// Eliminar api/evento/{id}/inscripcion/{personaId}
	@DeleteMapping("/{id}/inscripcion{personaId}")
	public ResponseEntity<Void> deletePersonaInEvento(
			@RequestHeader("Authorization") String authToken,
			@PathVariable String eventoId,
			@PathVariable String personaId) {
		Evento evento = eventoService.findByAuthToken(authToken);
		if(evento != null) {
			Persona existingEvento = eventoService.findPersonByEvent(eventoId, personaId);
			if(existingEvento != null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	// parámetros de Query String
	@GetMapping
	public ResponseEntity<List<Evento>> buscarEventos(
			@RequestParam(required = false) String fecha) {
		List<Evento> eventos = eventoService.searchByFilters(fecha);
		return new ResponseEntity<>(eventos, HttpStatus.OK);
	}
}
