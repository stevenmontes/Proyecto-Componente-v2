package com.proyecto.componentes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.proyecto.componentes.domain.Actor;
import com.proyecto.componentes.domain.Respuesta;
import com.proyecto.componentes.exception.ResourceNotFoundException;
import com.proyecto.componentes.repository.ActorRepository;


@RestController
@RequestMapping("/api/v1")
public class ActorController {

	@Autowired
	private ActorRepository repo;

	@GetMapping("/actores")
	public List<Actor> getAllActores() {
		return repo.findAll();
	}

	@GetMapping("/actores/{id}")
	public Actor getActores(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
		Actor nActor = new Actor();

		try {
			nActor = repo.findById(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return nActor;
	}

	@PostMapping("/actores")
	public ResponseEntity<Respuesta> createActor(@Valid @RequestBody Actor nActor)
			throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();

		try {
			repo.save(nActor);
			nRespuesta.Correcto("Actor registrado exitosamente");
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return ResponseEntity.ok(nRespuesta);
	}

	@PutMapping("/actores/{id}")
	public ResponseEntity<Respuesta> updateActor(@PathVariable(value = "id") int id,
			@Valid @RequestBody Actor nActor) throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();

		try {
			Actor updatedAct = repo.findById(id);
			updatedAct.setDescripcion(nActor.getDescripcion());
			updatedAct.setNombre(nActor.getNombre());

			repo.save(updatedAct);
			nRespuesta.Correcto("Actor actualizada con exito");
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return ResponseEntity.ok(nRespuesta);
	}



}