package com.proyecto.componentes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ACTOR")
public class Actor {
	@Id
	@Column(name = "ID")
	private int id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "ESTADO")
	private boolean estado;

//	@ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//        name = "TBL_ACTORxFUNCIONALIDAD", 
//        joinColumns =  @JoinColumn(name = "ID_ACTOR",referencedColumnName = "ID"), 
//        inverseJoinColumns =  @JoinColumn(name = "ID_FUNCIONALIDAD" ,referencedColumnName = "CODIGO")
//    )
//    Set<Funcionalidad> funcionalidades;

	public Actor(int id, String nombre, String descripcion, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public Actor() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

//	public Set<Funcionalidad> getFuncionalidades() {
//		return funcionalidades;
//	}
//
//	public void setFuncionalidades(Set<Funcionalidad> funcionalidades) {
//		this.funcionalidades = funcionalidades;
//	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + "]";
	}

}
