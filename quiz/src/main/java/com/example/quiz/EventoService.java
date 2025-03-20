package com.example.quiz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        Evento evento1 = new Evento(LocalDate.now(), personas_1);

        Persona andrea = new Persona("Andrea Giraldo", "andrea@example.com");
        Persona guarin = new Persona("Agudelo", "agudelo@example.com");
        List<Persona> personas_2 = new ArrayList<>();
        personas_2.add(andrea);
        personas_2.add(guarin);
        Evento evento2 = new Evento(LocalDate.now(), personas_1);

        save(evento1);
        save(evento2);
    }

    public Evento save(Evento evento) {
        return eventRepository.save(evento);
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
