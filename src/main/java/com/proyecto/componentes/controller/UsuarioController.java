package com.proyecto.componentes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.componentes.domain.Respuesta;
import com.proyecto.componentes.domain.Usuario;
import com.proyecto.componentes.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repo;

	@GetMapping("/usuarios")
	public ResponseEntity<?> getAllUsuarios() {
		try {
			return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			Respuesta res = new Respuesta();
			res.Error(e);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/usuarios/{cedula}")
	public ResponseEntity<?> getUsuarioByCedula(@PathVariable(value = "cedula") String cedula) {
		Usuario nUsuario;

		try {
			nUsuario = repo.findByCedula(cedula);

			if (nUsuario == null) {
				Respuesta res = new Respuesta();
				res.Error("Codigo ingresado no existe, no se puede obtener el usuario");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(nUsuario, HttpStatus.OK);
			}
		} catch (Exception e) {
			Respuesta res = new Respuesta();
			res.Error(e);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/usuarios")
	public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario nUsuario) {
		Respuesta res = new Respuesta();
		try {
			repo.save(nUsuario);
			res.Correcto("Usuario registrado exitosamente");
			return new ResponseEntity<>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			res.Error(e);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/usuarios/{cedula}")
	public ResponseEntity<?> updateUsuario(@PathVariable(value = "cedula") String cedula,
			@Valid @RequestBody Usuario detalleUsuario) {
		Respuesta res = new Respuesta();

		try {
			Usuario nUsuario = repo.findByCedula(cedula);

			if (nUsuario == null) {
				res.Error("Codigo ingresado no existe, no se puede actualizar el usuario");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			} else {
				nUsuario.setPrimerApellido(detalleUsuario.getPrimerApellido());
				nUsuario.setSegundoApellido(detalleUsuario.getSegundoApellido());
				nUsuario.setPrimerNombre(detalleUsuario.getPrimerNombre());
				nUsuario.setSegundoNombre(detalleUsuario.getSegundoNombre());
				nUsuario.setFechaNacimiento(detalleUsuario.getFechaNacimiento());
				res.Correcto("Usuario actualizado con exito");
				return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
			}

		} catch (Exception e) {
			res.Error(e);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/usuarios/{cedula}")
	public ResponseEntity<?> deleteUsuario(@PathVariable(value = "cedula") String cedula) {
		Respuesta res = new Respuesta();

		try {
			Usuario nUsuario = repo.findByCedula(cedula);

			if (nUsuario == null) {
				res.Error("Codigo ingresado no existe, no se puede eliminar el usuario");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			} else {
				repo.delete(nUsuario);
				res.Correcto("Usuario eliminado con exito");
				return new ResponseEntity<>(res, HttpStatus.OK);
			}
		} catch (Exception e) {
			res.Error(e);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
