package Interfaces;

public interface GraphsMethods {
	
	// PARAMETROS DOS METODOS COMENTADOS PARA AJUSTES FUTUROS. 
	
	public void readGraph(/*path*/); 
	
	public void readWeightedGraph(/*path*/); 
	
	public int getVertexNumber(/*graph*/); 
	
	public int getEdgeNumber (/*graph*/);
	
	public  float getMeanEdge(/*graph*/);
	
	public String graphRepresentation (/*graph, type*/);
	
	public String BFS(/*graph, v*/); 
	
	public String DFS(/*graph, v*/);
	
	public String SCC(/*graph */);
	
	public String shortestPath(/*v1, v2 */);
	
	public String mst(/*graph*/);
	

}
