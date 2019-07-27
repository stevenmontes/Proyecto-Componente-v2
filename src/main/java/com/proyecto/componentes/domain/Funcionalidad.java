package com.proyecto.componentes.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_FUNCIONAIDAD")
public class Funcionalidad {
	@Id
	@Column(name = "CODIGO")
	private String codigo;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name="PRIORIDAD")
	private int prioridad;
    @Column(name = "ID_PROYECTO")
    private String id_proyecto;
	
	@Column(name="ESTADO")
	private boolean estado;
	
	public Funcionalidad(String codigo, String nombre, String descripcion, int prioridad, String id_proyecto) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.id_proyecto = id_proyecto;
	}

	public Funcionalidad() {
		
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

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public String getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(String id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Funcionalidad [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", prioridad=" + prioridad + ", id_proyecto=" + id_proyecto + ", estado=" + estado + "]";
	}


	
	
}
