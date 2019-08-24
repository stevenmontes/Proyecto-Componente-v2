package com.proyecto.componentes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> getAllActores() {
		Object info;
		HttpStatus status;

		try {
			info = repo.findAll();
			status = HttpStatus.OK;
		} catch (Exception e) {
			Respuesta res = new Respuesta();
			res.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			info = res;
		}
		return new ResponseEntity<>(info, status);
	}

	@GetMapping("/actores/{id}")
	public ResponseEntity<?> getActores(@PathVariable(value = "id") int id) {
		HttpStatus status;
		Object info;

		try {

			Actor nActor = repo.findById(id);

			if (nActor == null) {
				Respuesta res = new Respuesta();
				res.Error("Codigo del actor no existe. No se puede obtener al actor.");
				status = HttpStatus.BAD_REQUEST;
				info = res;
			} else {
				info = nActor;
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			Respuesta error = new Respuesta();
			error.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			info = error;
		}

		return new ResponseEntity<>(info, status);
	}

	@PostMapping("/actores")
	public ResponseEntity<?> createActor(@Valid @RequestBody Actor nActor) {
		Respuesta nRespuesta = new Respuesta();
		HttpStatus status;

		try {
			repo.save(nActor);
			status = HttpStatus.CREATED;
			nRespuesta.Correcto("Actor registrado exitosamente");
		} catch (Exception e) {
			nRespuesta.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(nRespuesta, status);
	}

	@PutMapping("/actores/{id}")
	public ResponseEntity<?> updateActor(@PathVariable(value = "id") int id, @Valid @RequestBody Actor nActor)
			throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();
		HttpStatus status;

		try {
			Actor updatedAct = repo.findById(id);

			if (updatedAct == null) {
				nRespuesta.Error("Codigo del actor no existe, no se puede actualizar el actor.");
				status = HttpStatus.BAD_REQUEST;
			} else {
				updatedAct.setDescripcion(nActor.getDescripcion());
				updatedAct.setNombre(nActor.getNombre());
				repo.save(updatedAct);
				nRespuesta.Correcto("Actor actualizado con exito");
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			nRespuesta.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(nRespuesta, status);
	}

}