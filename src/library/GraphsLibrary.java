package library;

import java.io.*;

import entity.Graph;

public class GraphsLibrary implements GraphsMethods {

	// MAIN PARA REALIZAR TESTES RAPIDOS.
	// FUTURAMENTE CRIAR TESTES JUNIT
	public static void main(String[] args) throws FileNotFoundException {
		GraphsLibrary gl = new GraphsLibrary();
		Graph graph = gl.readWeightedGraph("graph.txt");
		System.out.println(gl.getEdgeNumber(graph));
		System.out.println(gl.getVertexNumber(graph));
		System.out.println(gl.BFS(graph, 1));
		System.out.println(gl.graphRepresentation(graph, "AL"));
	}

	private void addEdge(Graph graph, String fileInputLine) {
		String[] edge = fileInputLine.split(" ");
		int node1 = Integer.parseInt(edge[0]);
		int node2 = Integer.parseInt(edge[1]);
		graph.addEdge(node1, node2);
		graph.addEdge(node2, node1);
	}

	private void addWeightedEdge(Graph graph, String fileInputLine) {
		String[] edge = fileInputLine.split(" ");
		int node1 = Integer.parseInt(edge[0]);
		int node2 = Integer.parseInt(edge[1]);
		double weight = Double.parseDouble(edge[2]);
		graph.addEdge(node1, node2, weight);
		graph.addEdge(node2, node1, weight);
	}

	/** Metodo que le um arquivo de texto com um grafo e passa seus elementos para um array
	 *
	 * @param path
	 * 		Caminho no qual se encontra o arquivo com o grafo
	 *
	 * @throws FileNotFoundException
	 */
	public Graph readGraph(String path) throws FileNotFoundException {
		try {

			FileInputStream file = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(file);
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();

			int numVertices = Integer.parseInt(line);
			Graph graph = new Graph(numVertices);

			while ((line = br.readLine()) != null) {
				this.addEdge(graph, line);
			}

			return graph;

		} catch(IOException e) {
			throw new FileNotFoundException("Arquivo do grafo nao foi encontrado!");
		}
	}

	@Override
	public Graph readWeightedGraph(String path) throws FileNotFoundException {
		try {

			FileInputStream file = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(file);
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();

			int numVertices = Integer.parseInt(line);
			Graph graph = new Graph(numVertices);

			while ((line = br.readLine()) != null) {
				this.addWeightedEdge(graph, line);
			}

			return graph;

		} catch(IOException e) {
			throw new FileNotFoundException("Arquivo do grafo nao foi encontrado!");
		}
	}

	/**
	 * Retorna o numero de vertices do graph passado como parametro.
	 */
	@Override
	public int getVertexNumber(Graph graph) {
		return graph.getNumVertex();
	}

	@Override
	public int getEdgeNumber(Graph graph) {
		return graph.getNumEdges();
	}

	@Override
	public float getMeanEdge(Graph graph) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String graphRepresentation(Graph graph, String type) {
		return graph.graphRepresentation(type);
	}

	@Override
	public String BFS(Graph graph, int v) {
		String out = "";
		for (String line : graph.BFS(v)) {
			out += line;
		}
		return out;
	}

	@Override
	public String DFS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean connected(Graph graph) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String shortestPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mst() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
