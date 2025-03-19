package com.example.quiz;

import java.util.UUID;

public class Persona {
	private String nombre;
	private String email;
	private String id;
	
	public Persona() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Persona(String nombre, String email) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
