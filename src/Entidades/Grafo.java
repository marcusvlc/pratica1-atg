package Entidades;

public class Grafo {
	
	// TALVEZ POSSUIR UM ARRAY COM OS VERTICES (OBJETOS)
	// ATRIBUTO NUMERO DE VERTICES SERIA DISPENSAVEL NESSE CASO
	
	private int numVertices;
	
	public Grafo(int numVertices) {
		this.numVertices = numVertices;
	}
	
	public int getNumVertices() {
		return this.numVertices;
	}
	
	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}
}
