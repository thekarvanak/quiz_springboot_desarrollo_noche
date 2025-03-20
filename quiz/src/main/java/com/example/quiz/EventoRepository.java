/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.quiz;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class EventoRepository {
    
    // Simulamos una base de datos con un Map
    private final Map<String, Evento> baseDeDatos = new HashMap<>();
    
    // Simulamos almacén de tokens de autorización
    private final Map<String, String> authTokens = new HashMap<>();
    
     // Guardar un evento
    public Evento save(Evento evento) {
        baseDeDatos.put(evento.getId(), evento);
        // Inicializamos estructuras relacionadas
        authTokens.put("token-" + evento.getId(), evento.getId());
        return evento;
    }
    
    
}
