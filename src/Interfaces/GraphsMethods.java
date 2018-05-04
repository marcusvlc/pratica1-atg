package Interfaces;

import Entidades.Grafo;

public interface GraphsMethods {
	
	// PARAMETROS DOS METODOS COMENTADOS PARA AJUSTES FUTUROS. 
	
	public void readGraph (String path); 
	
	public void readWeightedGraph(String path); 
	
	public int getVertexNumber(Grafo grafo); 
	
	public int getEdgeNumber (Grafo grafo);
	
	public  float getMeanEdge(Grafo grafo);
	
	public String graphRepresentation (/*graph, type*/);
	
	public String BFS(/*graph, v*/); 
	
	public String DFS(/*graph, v*/);
	
	public String SCC(/*graph */);
	
	public String shortestPath(/*v1, v2 */);
	
	public String mst(/*graph*/);
	

}
