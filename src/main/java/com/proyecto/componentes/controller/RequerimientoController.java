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

import com.proyecto.componentes.domain.Requerimiento;
import com.proyecto.componentes.domain.Respuesta;
import com.proyecto.componentes.repository.RequerimientoRepository;

@RestController
@RequestMapping("/api/v1")
public class RequerimientoController {
	@Autowired
	private RequerimientoRepository repoReq;

	@GetMapping("/requerimientos/funcionalidad/{codigoFuncionalidad}")
	public ResponseEntity<?> getAllRequerimientos(
			@PathVariable(value = "codigoFuncionalidad") String codigoFuncionalidad) {
		Object info;
		HttpStatus status;

		try {
			info = repoReq.getAllByFuncionalidad(codigoFuncionalidad);
			status = HttpStatus.OK;
		} catch (Exception e) {
			Respuesta res = new Respuesta();
			res.Error(e);
			info = res;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(info, status);
	}

	@GetMapping("/requerimientos/{codigo}")
	public ResponseEntity<?> getRequerimiento(@PathVariable(value = "codigo") String codigo) {
		Object info;
		HttpStatus status;

		try {
			Requerimiento nReq = repoReq.findByCodigo(codigo);

			if (nReq == null) {
				Respuesta res = new Respuesta();
				res.Error("Codigo ingresado no existe. No se puede obtener el requerimiento.");
				info = res;
				status = HttpStatus.BAD_REQUEST;
			} else {
				info = nReq;
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

	@PostMapping("/requerimientos")
	public ResponseEntity<?> createRequerimiento(@Valid @RequestBody Requerimiento nReq) {
		Respuesta nRespuesta = new Respuesta();
		HttpStatus status;

		try {
			repoReq.save(nReq);
			nRespuesta.Correcto("Requerimiento registrado con exito");
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			nRespuesta.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(nRespuesta, status);
	}

	@PutMapping("/requerimientos/{codigo}")
	public ResponseEntity<Respuesta> updateRequerimiento(@Valid @RequestBody Requerimiento nReq,
			@PathVariable(value = "codigo") String codigo) {
		Respuesta nRes = new Respuesta();
		HttpStatus status;

		try {
			Requerimiento updatedReq = repoReq.findByCodigo(codigo);

			if (updatedReq == null) {
				nRes.Error("Codigo ingresado no existe, no se puede actualizar el requerimiento.");
				status = HttpStatus.BAD_REQUEST;
			} else {
				updatedReq.setDescripcion(nReq.getDescripcion());
				repoReq.save(updatedReq);
				nRes.Correcto("Requerimiento actualizado con exito");
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			nRes.Error(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(nRes, status);
	}
}
