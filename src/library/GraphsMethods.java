package library;

import entity.Graph;
import entity.Node;

import java.io.FileNotFoundException;

public interface GraphsMethods {
	
	// PARAMETROS DOS METODOS COMENTADOS PARA AJUSTES FUTUROS. 
	
	public Graph readGraph (String path) throws FileNotFoundException;
	
	public Graph readWeightedGraph(String path) throws FileNotFoundException; 
	
	public int getVertexNumber(Graph graph);
	
	public int getEdgeNumber (Graph graph);
	
	public  float getMeanEdge(Graph graph);
	
	public String graphRepresentation (Graph graph, String type);
	
	public String BFS(Graph graph, int v);
	
	public String DFS(Graph graph, int v);
	
	public boolean connected(Graph graph);
	
	public String shortestPath(Graph graph, Node v1, Node v2);
	
	public String mst(/*graph*/);
	

}