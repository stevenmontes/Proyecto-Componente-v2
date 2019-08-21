package com.proyecto.componentes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Proyecto> getAllProyectos() {
		return repo.findAll();
	}

	@GetMapping("/proyectos/{codigo}")
	public Proyecto getProyecto(@PathVariable(value = "codigo") String codigo) throws ResourceNotFoundException {
		Proyecto nProyecto = new Proyecto();

		try {
			nProyecto = repo.findByCodigo(codigo);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return nProyecto;
	}

	@PostMapping("/proyectos")
	public ResponseEntity<Respuesta> createProyecto(@Valid @RequestBody Proyecto nProyecto)
			throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();

		try {
			repo.save(nProyecto);
			nRespuesta.Correcto("Proyecto registrado exitosamente");
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return ResponseEntity.ok(nRespuesta);
	}

	@PutMapping("/proyectos/{codigo}")
	public ResponseEntity<Respuesta> updateProyecto(@PathVariable(value = "codigo") String codigo,
			@Valid @RequestBody Proyecto nProyecto) throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();

		try {
			Proyecto updatedProy = repo.findByCodigo(codigo);
			updatedProy.setDescripcion(nProyecto.getDescripcion());
			updatedProy.setNombre(nProyecto.getNombre());
			updatedProy.setVersion(nProyecto.getVersion());
			repo.save(updatedProy);
			nRespuesta.Correcto("Proyecto actualizado con exito");
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return ResponseEntity.ok(nRespuesta);
	}
}
