package com.proyecto.componentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.componentes.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Usuario findByCedula(String cedula);

}
