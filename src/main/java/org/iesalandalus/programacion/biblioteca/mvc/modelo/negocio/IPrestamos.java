package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public interface IPrestamos {

	/** Método para obtener una copia profunda de los préstamos */
	List<Prestamo> get();

	/** Método para obtener el tamaño */
	int getTamano();

	/**
	 * Método para obtener una copia de todos los préstamos que ha realizado un
	 * alumno.
	 */
	List<Prestamo> get(Alumno alumno);

	/**
	 * Método para obtener una copia de todos los préstamos que se han realizado de
	 * un libro en concreto.
	 */
	List<Prestamo> get(Libro libro);

	/**
	 * Método para obtener una copia de todos los préstamos realizados en una fecha
	 * concreta
	 */
	List<Prestamo> get(LocalDate fecha);

	Map<Curso, Integer> getEstadisticaMensualPorCurso(LocalDate mes);

	/**
	 * Método para generar un nuevo préstamo
	 * 
	 * @throws OperationNotSupportedException
	 */
	void prestar(Prestamo prestamo) throws OperationNotSupportedException;

	/**
	 * Método para realizar una devolución de un prestamo en una fecha concreta
	 * pasados ambos por parámetro
	 * 
	 * @throws OperationNotSupportedException
	 */
	void devolver(Prestamo prestamo, LocalDate fecha) throws OperationNotSupportedException;

	/** Método para buscar un préstamo */
	Prestamo buscar(Prestamo prestamo);

	/**
	 * Método para borrar un préstamo
	 * 
	 * @throws OperationNotSupportedException
	 */
	void borrar(Prestamo prestamo) throws OperationNotSupportedException;

}