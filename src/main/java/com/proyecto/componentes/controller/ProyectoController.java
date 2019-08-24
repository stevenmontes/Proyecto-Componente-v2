package com.proyecto.componentes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.componentes.domain.Proyecto;
import com.proyecto.componentes.domain.Respuesta;
import com.proyecto.componentes.exception.ResourceNotFoundException;
import com.proyecto.componentes.repository.ProyectoRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {
	@Autowired
	private ProyectoRepository repo;

	@GetMapping("/proyectos")
	public ResponseEntity<?> getAllProyectos() {
		try {
			List<Proyecto> proyectos = repo.findAll();
			return new ResponseEntity<>(proyectos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al obtener los proyectos", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/proyectos/{codigo}")
	public ResponseEntity<?> getProyecto(@PathVariable(value = "codigo") String codigo) {
		Proyecto nProyecto = new Proyecto();
		Respuesta nRes = new Respuesta();
		HttpStatus status;
		
		try {
			nProyecto = repo.findByCodigo(codigo);
			
			if(nProyecto == null) {
				nRes.Error("Codigo del proyecto no existe");
				status = HttpStatus.BAD_REQUEST;
			} else {
				nRes.Correcto("200", nProyecto);
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			nRes.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(nRes, status);
	}

	@PostMapping("/proyectos")
	public ResponseEntity<?> createProyecto(@Valid @RequestBody Proyecto nProyecto) throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();
		HttpStatus status;
		
		try {
			repo.save(nProyecto);
			nRespuesta.Correcto("Proyecto registrado exitosamente");
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			nRespuesta.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(nRespuesta, status);
	}

	@PutMapping("/proyectos/{codigo}")
	public ResponseEntity<?> updateProyecto(@PathVariable(value = "codigo") String codigo,
			@Valid @RequestBody Proyecto nProyecto) throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();
		HttpStatus status;

		try {
			Proyecto updatedProy = repo.findByCodigo(codigo);
			updatedProy.setDescripcion(nProyecto.getDescripcion());
			updatedProy.setNombre(nProyecto.getNombre());
			updatedProy.setVersion(nProyecto.getVersion());
			repo.save(updatedProy);
			nRespuesta.Correcto("Proyecto actualizado con exito");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			nRespuesta.Error(e);
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(nRespuesta, status);
	}
}
