package com.proyecto.componentes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.componentes.domain.Usuario;
import com.proyecto.componentes.exception.ResourceNotFoundException;
import com.proyecto.componentes.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repo;

	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
		return repo.findAll();
	}

	@GetMapping("/usuarios/{cedula}")
	public ResponseEntity<Usuario> getUsuarioByCedula(@PathVariable(value = "cedula") String cedula)
			throws ResourceNotFoundException {
		Usuario nUsuario;

		try {
			nUsuario = repo.findByCedula(cedula);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Usuario no encontrado con esta cedula :: " + cedula);
		}

		return ResponseEntity.ok().body(nUsuario);
	}

	@PostMapping("/usuarios")
	public Usuario createUsuario(@Valid @RequestBody Usuario nUsuario) {
		return repo.save(nUsuario);
	}

	@PutMapping("/usuarios/{cedula}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "cedula") String cedula,
			@Valid @RequestBody Usuario detalleUsuario) throws ResourceNotFoundException {
		final Usuario updatedUsuario;

		try {
			Usuario nUsuario = repo.findByCedula(cedula);
			nUsuario.setPrimerApellido(detalleUsuario.getPrimerApellido());
			nUsuario.setSegundoApellido(detalleUsuario.getSegundoApellido());
			nUsuario.setPrimerNombre(detalleUsuario.getPrimerNombre());
			nUsuario.setSegundoNombre(detalleUsuario.getSegundoNombre());
			nUsuario.setFechaNacimiento(detalleUsuario.getFechaNacimiento());
			updatedUsuario = repo.save(nUsuario);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Usuario no encontrado con este id ::" + cedula);
		}

		return ResponseEntity.ok(updatedUsuario);
	}

	@DeleteMapping("/usuarios/{cedula}")
	public Map<String, Boolean> deleteUsuario(@PathVariable(value = "cedula") String cedula)
			throws ResourceNotFoundException {
		Map<String, Boolean> response = new HashMap<>();

		try {
			Usuario nUsuario = repo.findByCedula(cedula);
			repo.delete(nUsuario);
			response.put("eliminado", Boolean.TRUE);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Usuario no encontrado con este id ::" + cedula);
		}

		return response;
	}

}
