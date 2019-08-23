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

import com.proyecto.componentes.domain.Requerimiento;
import com.proyecto.componentes.domain.Respuesta;
import com.proyecto.componentes.exception.ResourceNotFoundException;
import com.proyecto.componentes.repository.RequerimientoRepository;

@RestController
@RequestMapping("/api/v1")
public class RequerimientoController {
	@Autowired
	private RequerimientoRepository repoReq;

	@GetMapping("/requerimientos/funcionalidad/{codigoFuncionalidad}")
	public List<Requerimiento> getAllRequerimientos(@PathVariable(value = "codigoFuncionalidad") String codigoFuncionalidad) {
		return repoReq.getAllByFuncionalidad(codigoFuncionalidad);
	}

	@GetMapping("/requerimientos/{codigo}")
	public Requerimiento getRequerimiento(@PathVariable(value = "codigo") String codigo)
			throws ResourceNotFoundException {
		Requerimiento nReq = new Requerimiento();

		try {
			nReq = repoReq.findByCodigo(codigo);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return nReq;
	}

	@PostMapping("/requerimientos")
	public ResponseEntity<Respuesta> createRequerimiento(@Valid @RequestBody Requerimiento nReq)
			throws ResourceNotFoundException {
		Respuesta nRespuesta = new Respuesta();

		try {
			repoReq.save(nReq);
			nRespuesta.Correcto("Requerimiento registrado con exito");
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return ResponseEntity.ok(nRespuesta);
	}

	@PutMapping("/requerimientos/{codigo}")
	public ResponseEntity<Respuesta> updateRequerimiento(@Valid @RequestBody Requerimiento nReq,
			@PathVariable(value = "codigo") String codigo) throws ResourceNotFoundException {
		Respuesta nRes = new Respuesta();

		try {
			Requerimiento updatedReq = repoReq.findByCodigo(codigo);
			updatedReq.setDescripcion(nReq.getDescripcion());
			repoReq.save(updatedReq);
			nRes.Correcto("Requerimiento actualizado con exito");

		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

		return ResponseEntity.ok(nRes);
	}
}
