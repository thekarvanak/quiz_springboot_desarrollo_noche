package com.example.quiz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Evento {
	private String id;
	private LocalDate fecha;
	private List<Persona> invitados;
	
	public Evento() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Evento(LocalDate fecha) {
		this.fecha = fecha;
		invitados = new ArrayList<>();
		this.id = UUID.randomUUID().toString();
	}
        
        public String getId(){
            return id;
        }
        
        public List<Persona> getInvitados(){
        return invitados;
        }
                
}
