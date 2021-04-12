package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

public interface ILibros {

	/** Método para devolver una copia profunda de Libros */
	List<Libro> get();

	/** Devuelve el tamaño de los libros ya insertados */
	int getTamano();

	/** Método para insertar libros nuevos */
	void insertar(Libro libro) throws OperationNotSupportedException;

	/** Método para buscar un libro */
	Libro buscar(Libro libro);

	/** Método para borrar un libro. */
	void borrar(Libro libro) throws OperationNotSupportedException;

}