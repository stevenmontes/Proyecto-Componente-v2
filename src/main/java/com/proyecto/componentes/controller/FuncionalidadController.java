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

import com.proyecto.componentes.domain.Funcionalidad;
import com.proyecto.componentes.domain.Respuesta;
import com.proyecto.componentes.repository.FuncionalidadRepository;

@RestController
@RequestMapping("/api/v1")
public class FuncionalidadController {

	@Autowired
	private FuncionalidadRepository repo;

	@GetMapping("/funcionalidades")
	public ResponseEntity<?> getAllFuncionalidades() {
		HttpStatus status;
		Object info;

		try {
			info = repo.findAll();
			status = HttpStatus.OK;
		} catch (Exception e) {
			Respuesta res = new Respuesta();
			res.Error(e);
			info = res;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(info, status);
	}

	@GetMapping("/funcionalidades/{codigo}")
	public ResponseEntity<?> getFuncionalidad(@PathVariable(value = "codigo") String codigo) {
		Object info;
		HttpStatus status;

		try {
			Funcionalidad nFuncionalidad = repo.findByCodigo(codigo);

			if (nFuncionalidad == null) {
				Respuesta res = new Respuesta();
				res.Error("El codigo ingresado no existe. No se puede obtener la funcionalidad");
				status = HttpStatus.BAD_REQUEST;
				info = res;
			} else {
				info = nFuncionalidad;
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			Respuesta res = new Respuesta();
			res.Error(e);
			info = res;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(info, status);
	}

	@PostMapping("/funcionalidades")
	public ResponseEntity<?> createFuncionalidad(@Valid @RequestBody Funcionalidad nFuncionalidad) {
		Respuesta nRespuesta = new Respuesta();
		HttpStatus status;

		try {
			repo.save(nFuncionalidad);
			nRespuesta.Correcto("Funcionalidad registrada exitosamente");
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			nRespuesta.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(nRespuesta, status);
	}

	@PutMapping("/funcionalidades/{codigo}")
	public ResponseEntity<?> updateFuncionalidad(@PathVariable(value = "codigo") String codigo,
			@Valid @RequestBody Funcionalidad nFuncionalidad) {
		Respuesta nRespuesta = new Respuesta();
		HttpStatus status;

		try {
			Funcionalidad updatedFun = repo.findByCodigo(codigo);

			if (updatedFun == null) {
				nRespuesta.Error("Codigo no existe, no se puede actualizar la funcionalidad");
				status = HttpStatus.BAD_REQUEST;
			} else {
				updatedFun.setDescripcion(nFuncionalidad.getDescripcion());
				updatedFun.setNombre(nFuncionalidad.getNombre());
				updatedFun.setPrioridad(nFuncionalidad.getPrioridad());
				repo.save(updatedFun);
				nRespuesta.Correcto("Funcionalidad actualizada con exito");
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			nRespuesta.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(nRespuesta, status);
	}

}
