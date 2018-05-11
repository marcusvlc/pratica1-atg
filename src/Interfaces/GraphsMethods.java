package Interfaces;

import Entidades.Graph;

import java.io.FileNotFoundException;

public interface GraphsMethods {
	
	// PARAMETROS DOS METODOS COMENTADOS PARA AJUSTES FUTUROS. 
	
	public Graph readGraph (String path) throws FileNotFoundException;
	
	public void readWeightedGraph(String path); 
	
	public int getVertexNumber(Graph graph);
	
	public int getEdgeNumber (Graph graph);
	
	public  float getMeanEdge(Graph graph);
	
	public String graphRepresentation (/*graph, type*/);
	
	public String BFS(Graph graph, int v);
	
	public String DFS(/*graph, v*/);
	
	public String SCC(/*graph */);
	
	public String shortestPath(/*v1, v2 */);
	
	public String mst(/*graph*/);
	

}
