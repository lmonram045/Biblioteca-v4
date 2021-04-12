package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

public class AudioLibro extends Libro {
	// Constantes de la clase
	private static final int MINUTOS_PARA_RECOMPENSA = 15;
	private static final float PUNTOS_PREMIO = 0.25f;
	
	// Variables
	private int duracion;
	
	/** Constructor con parámetros */
	public AudioLibro(String titulo, String autor, int duracion) {
		super(titulo, autor);
		setDuracion(duracion);
	}
	
	/** Constructor copia */
	public AudioLibro(AudioLibro libro) {
		super(libro);
		setDuracion(libro.getDuracion());
	}
	
	/** Método para obtener la duracion de un libro */
	public int getDuracion() {
		return duracion;
	}
	
	/** Método para asignar la duración */
	private void setDuracion(int duracion) {
		if (duracion < 1)
			throw new IllegalArgumentException("ERROR: La duración debe ser mayor que cero.");
		
		this.duracion = duracion;
	}
	
	/** Método para obtener los puntos de un audiolibro */
	public float getPuntos() {
		return ((duracion / MINUTOS_PARA_RECOMPENSA) + 1) * PUNTOS_PREMIO;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "título=" + super.titulo + ", autor=" + super.autor + ", duración=" + duracion;
	}
}
