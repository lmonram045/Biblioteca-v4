package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

import java.util.Objects;

public abstract class Libro {
	
	// Variables de la clase
	protected String titulo;
	protected String autor;
	
	/** Constructor con parámetros */
	public Libro(String titulo, String autor) {
		setTitulo(titulo);
		setAutor(autor);
	}

	/** Constructor copia */
	public Libro(Libro libro) {
		// Si el libro es nulo salta excepción
		if (libro == null) 
			throw new NullPointerException("ERROR: No es posible copiar un libro nulo.");
		
		setTitulo(libro.getTitulo());
		setAutor(libro.getAutor());
	}

	/** Método para devolver un libro ficticio */
	public static Libro getLibroFicticio(String titulo, String autor) {
		return new LibroEscrito(titulo, autor, 1527);
	}
	
	/** Método para obtener los puntos obtenidos por libro */
	public abstract float getPuntos();
	
	public String getTitulo() {
		return titulo;
	}
	
	protected void setTitulo(String titulo) {
		// Comprueba que no sea nulo el nombre
		if (titulo == null)
			throw new NullPointerException("ERROR: El título no puede ser nulo.");
	
		// Compruebo si al eliminar los espacios, el nombre está vacío
		if (titulo.trim().isEmpty()) 
			throw new IllegalArgumentException("ERROR: El título no puede estar vacío.");
				
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	protected void setAutor(String autor) {
		// Comprobamos que el autor no sea nulo
		if (autor == null)
			throw new NullPointerException("ERROR: El autor no puede ser nulo.");
		
		// Comprobamos que el nombre del autor no está vacío.
		if (autor.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El autor no puede estar vacío.");
				
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Libro))
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "título=" + titulo + ", autor=" + autor;
	}

}
