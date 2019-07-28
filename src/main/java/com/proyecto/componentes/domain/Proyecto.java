package com.proyecto.componentes.domain;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PROYECTO")
public class Proyecto {
	@Id
	@Column(name = "CODIGO")
	private String codigo;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "VERSION")
	private String version;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TBL_USUARIOxPROYECTO", 
	joinColumns = @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "CODIGO"), 
	inverseJoinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "CEDULA"))
	private Set<Usuario> usuarios;

	public Proyecto() {
		super();
	}

	public Proyecto(String codigo, String nombre, String descripcion, String version, Usuario... nUsuario) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.version = version;
		this.usuarios = Stream.of(nUsuario).collect(Collectors.toSet());
		this.usuarios.forEach(x -> x.getProyectos().add(this));
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Proyecto [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", version="
				+ version + "]";
	}

}
