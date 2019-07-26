package com.proyecto.componentes.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_USUARIO")
public class Usuario {
	@Id
	@Column(name = "CEDULA")
	private String cedula;
	@Column(name = "PRIMER_NOMBRE")
	private String primerNombre;
	@Column(name = "SEGUNDO_NOMBRE")
	private String segundoNombre;
	@Column(name = "PRIMER_APELLIDO")
	private String primerApellido;
	@Column(name = "SEGUNDO_APELLIDO")
	private String segundoApellido;
	@Column(name = "FECHA_NACIMIENTO")
	private Date fechaNacimiento;
	@Column(name = "CLAVE")
	private String clave;

	public Usuario() {

	}

	public Usuario(String cedula, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, Date fechaNacimiento, String clave) {
		super();
		this.cedula = cedula;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.fechaNacimiento = fechaNacimiento;
		this.clave = clave;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
