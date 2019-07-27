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

import com.proyecto.componentes.domain.Funcionalidad;
import com.proyecto.componentes.domain.Respuesta;
import com.proyecto.componentes.exception.ResourceNotFoundException;
import com.proyecto.componentes.repository.FuncionalidadRepository;




@RestController
@RequestMapping("/api/v1")
public class FuncionalidadController {
	
	@Autowired
	private FuncionalidadRepository repo;

	@GetMapping("/funcionalidad")
	public List<Funcionalidad> getAllProyectos() {
		return repo.findAll();
	}
	
	@GetMapping("/funcionalidad/{codigo}")
	public Funcionalidad getFuncionalidad(@PathVariable(value = "codigo") String codigo) throws ResourceNotFoundException {
		Funcionalidad nFuncionalidad = new Funcionalidad();

		try {
			nFuncionalidad = repo.findByCodigo(codigo);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return nFuncionalidad;
	}
	@PostMapping("/funcionalidad")
	public ResponseEntity<Respuesta> createFuncionalidad(@Valid @RequestBody Funcionalidad nFuncionalidad)
			throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();

		try {
			repo.save(nFuncionalidad);
			nRespuesta.Correcto("Funcionalidad registrada exitosamente");
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return ResponseEntity.ok(nRespuesta);
	}

	@PutMapping("/funcionalidad/{codigo}")
	public ResponseEntity<Respuesta> updateFuncionalidad(@PathVariable(value = "codigo") String codigo,
			@Valid @RequestBody Funcionalidad nFuncionalidad) throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();

		try {
			Funcionalidad updatedFun = repo.findByCodigo(codigo);
			updatedFun.setDescripcion(nFuncionalidad.getDescripcion());
			updatedFun.setNombre(nFuncionalidad.getNombre());
			updatedFun.setPrioridad(nFuncionalidad.getPrioridad());
			repo.save(updatedFun);
			nRespuesta.Correcto("Funcionalidad actualizada con exito");
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return ResponseEntity.ok(nRespuesta);
	}
	
}
