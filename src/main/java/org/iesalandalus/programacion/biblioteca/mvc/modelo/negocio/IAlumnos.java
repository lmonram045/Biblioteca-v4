package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;

public interface IAlumnos {

	/** Método para obtener todos los alumnos ordenados por nombre */
	List<Alumno> get();

	int getTamano();

	/** Método para insertar un alumno nuevo */
	void insertar(Alumno alumno) throws OperationNotSupportedException;

	/** Método para buscar un alumno */
	Alumno buscar(Alumno alumno);

	/** Método para borrar un alumno */
	void borrar(Alumno alumno) throws OperationNotSupportedException;

}