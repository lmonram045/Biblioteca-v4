package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

public class LibroEscrito extends Libro {
	// Constantes de la clase
	private static final int PAGINAS_PARA_RECOMPENSA = 25;
	private static final float PUNTOS_PREMIO = 0.5f;
	// Variables
	private int numPaginas;

	/** Constructor por defecto */
	public LibroEscrito(String titulo, String autor, int numPaginas) {
		// Referencio al constructor de la clase padre y llamo al método de esta clase
		// para asignar el número de páginas.
		super(titulo, autor);
		setNumPaginas(numPaginas);
	}

	/** Constructor copia */
	public LibroEscrito(LibroEscrito libro) {
		// Referencia al constructor de la clase padre y llamo al método de esta clase
		// para asignar el número de páginas de el libro pasado por argumento.
		super(libro);
		setNumPaginas(libro.getNumPaginas());
	}
	
	/** Método para obtener el número de páginas */
	public int getNumPaginas() {
		return numPaginas;
	}
	
	/** Método para asignar el número de páginas */
	private void setNumPaginas(int numPaginas) {
		if (numPaginas < 1) 
			throw new IllegalArgumentException("ERROR: El número de páginas debe ser mayor que cero.");
		
		this.numPaginas = numPaginas;
	}
	
	/** Método para obtener los puntos obtenidos por libro */
	@Override
	public float getPuntos() {
		return ((numPaginas / PAGINAS_PARA_RECOMPENSA) + 1) * PUNTOS_PREMIO;
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
		return "título=" + super.titulo + ", autor=" + super.autor + ", número de páginas=" + numPaginas;
	}
	
	
}
