package library;

import entity.Graph;

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
	
	public String DFS(/*graph, v*/);
	
	public boolean connected(Graph graph);
	
	public String shortestPath(/*v1, v2 */);
	
	public String mst(/*graph*/);
	

}
