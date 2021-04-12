package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public interface IModelo {

	/** Método para insertar un alumno */
	void insertar(Alumno alumno) throws OperationNotSupportedException;

	/** Método para insertar un libro */
	void insertar(Libro libro) throws OperationNotSupportedException;

	/** Método para realizar un préstamo */
	void prestar(Prestamo prestamo) throws OperationNotSupportedException;

	/** Método para realizar una devolución */
	void devolver(Prestamo prestamo, LocalDate fecha) throws OperationNotSupportedException;

	/** Método para buscar un alumno */
	Alumno buscar(Alumno alumno);

	/** Método para buscar un libro */
	Libro buscar(Libro libro);

	/** Método para buscar un préstamo */
	Prestamo buscar(Prestamo prestamo);

	/** Método para borrar un alumno */
	void borrar(Alumno alumno) throws OperationNotSupportedException;

	/** Método para borrar un libro */
	void borrar(Libro libro) throws OperationNotSupportedException;

	/** Método para borrar un préstamo */
	void borrar(Prestamo prestamo) throws OperationNotSupportedException;

	/** Método para obtener una copia profunda de los alumnos */
	List<Alumno> getAlumnos();

	/** Método para obtener una copia profunda de los libros */
	List<Libro> getLibros();

	/** Método para obtener una copia profunda de los préstamos */
	List<Prestamo> getPrestamos();

	/** Método para obtener una copia profunda de los préstamos de un alumno */
	List<Prestamo> getPrestamos(Alumno alumno);

	/** Método para obtener una copia profunda de los préstamos de un libro */
	List<Prestamo> getPrestamos(Libro libro);

	/** Método para obtener una copia profunda de los préstamos realizados en una fecha concreta */
	List<Prestamo> getPrestamos(LocalDate fecha);

	/** Método para obtener una copia profunda de los préstamos realizados en un mes ordenados por curso */
	Map<Curso, Integer> getEstadisticaMensualPorCurso(LocalDate mes);

}