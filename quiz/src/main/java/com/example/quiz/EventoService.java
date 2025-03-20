package com.example.quiz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    private final EventoRepository eventRepository;

    @Autowired
    public EventoService(EventoRepository eventRepository) {
        this.eventRepository = eventRepository;
        
        // Inicializamos algunos datos de ejemplo
        initSampleData();
    }

    private void initSampleData(){
        Persona juan = new Persona("Juan Pérez", "juan@example.com");
        Persona maria = new Persona("María López", "maria@example.com");
        Persona carlos = new Persona("Carlos Ruiz", "carlos@example.com");
        List<Persona> personas_1 = new ArrayList<>();
        personas_1.add(carlos);
        personas_1.add(maria);
        personas_1.add(juan);
        Evento evento1 = new Evento(LocalDate.now());

        Persona andrea = new Persona("Andrea Giraldo", "andrea@example.com");
        Persona guarin = new Persona("Agudelo", "agudelo@example.com");
        List<Persona> personas_2 = new ArrayList<>();
        personas_2.add(andrea);
        personas_2.add(guarin);
        Evento evento2 = new Evento(LocalDate.now());

        save(evento1);
        save(evento2);
        int lenEvento1 = personas_1.size();
        int lenEvento2 = personas_2.size();
        for(int i = 0; i < lenEvento1; i++) {
        	savePerson(evento1.getId(), personas_1.get(i));
        }
        for(int i = 0; i < lenEvento2; i++) {
        	savePerson(evento2.getId(), personas_2.get(i));
        }
    }
    
    public Map<String, String> getTrack() {
    	return eventRepository.getTrack();
    }

    public Evento save(Evento evento) {
        return eventRepository.save(evento);
    }
    
    public Persona savePerson(String idEvento, Persona persona) {
    	return eventRepository.savePerson(idEvento, persona);
    }
    
    public String findPersonByTrackingId(String idEvento, String trackingId) {
    	return eventRepository.findPersonByTrackingId(idEvento, trackingId);
    }

    public Persona findPersonByEvent(String idEvento, String idPersona){
        return eventRepository.findPersonByEvent(idEvento, idPersona);
    }

    public void deletePersonByEvent(String idEvento, String idPersona){
        eventRepository.deletePersonByEvent(idEvento, idPersona);
    }

    public Evento findByAuthToken(String authToken){
        return eventRepository.findByAuthToken(authToken);
    }

    public List<Evento> searchByFilters(LocalDate fecha){
        return eventRepository.searchByFilters(fecha);
    }
}