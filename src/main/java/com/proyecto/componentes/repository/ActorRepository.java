package com.proyecto.componentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.componentes.domain.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	Actor findById(int id);
}
