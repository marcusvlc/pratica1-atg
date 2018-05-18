package library;

import java.io.*;

import entity.Graph;
import entity.Node;

public class GraphsLibrary implements GraphsMethods {

	
	/**
	 * Adiciona uma aresta simples ao grafo
	 * @param graph
	 * 	Recebe o grafo na qual a aresta ira ser adicionada
	 * @param fileInputLine
	 * 	Recebe a linha que contem os valores
	 */
	private void addEdge(Graph graph, String fileInputLine) {
		String[] edge = fileInputLine.split(" ");
		int node1 = Integer.parseInt(edge[0]);
		int node2 = Integer.parseInt(edge[1]);
		graph.addEdge(node1, node2);
	}
	
	/**
	 * Adiciona uma aresta com peso ao grafo
	 * @param graph
	 *	 Recebe o grafo na qual a aresta ira ser adicionada
	 * @param fileInputLine
	 * 	Recebe a linha que contem os valores
	 */
	private void addWeightedEdge(Graph graph, String fileInputLine) {
		String[] edge = fileInputLine.split(" ");
		int node1 = Integer.parseInt(edge[0]);
		int node2 = Integer.parseInt(edge[1]);
		double weight = Double.parseDouble(edge[2]);
		graph.addEdge(node1, node2, weight);
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

			graph.setDefault_weigth(false);
			
			return graph;

		} catch(IOException e) {
			throw new FileNotFoundException("Arquivo do grafo nao foi encontrado!");
		}
	}
	
	
	/** Le um arquivo que contem um grafo com pesos e o transforma em uma entidade grafo
	 * @param path
	 * 	Recebe o caminho no disco no qual o grafo esta implementado.
	 */
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

			graph.setDefault_weigth(true);
			
			return graph;

		} catch(IOException e) {
			throw new FileNotFoundException("Arquivo do grafo nao foi encontrado!");
		}
	}

	/**
	 * Retorna o numero de vertices de um grafo.
	 * @param graph
	 * 	Recebe o grafo na qual sera calculado o numero de vertices
	 */
	@Override
	public int getVertexNumber(Graph graph) {
		return graph.getNumVertex();
	}
	
	/**	Retorna o numero de arestas de um grafo.
	 * @param graph
	 * Recebe o grafo na qual sera calculado o numero de arestas
	 */
	@Override
	public int getEdgeNumber(Graph graph) {
		return graph.getNumEdges();
	}
	
	/**	Retorna a media dos pesos das arestas do grafo.
	 * @param graph
	 *  Recebe um grafo com pesos
	 */
	@Override
	public float getMeanEdge(Graph graph) {
		float sum = 0;
		
		for(int i = 0; i < graph.getAdjacencyList().length; i++) {
			if(graph.getAdjacencyList()[i] != null) {
				for(int j = 0; j < graph.getAdjacencyList()[i].getAdjacentNodes().size(); j++) {
					sum+= graph.getAdjacencyList()[i].getAdjacentNodes().get(j).getWeight();
				}
			}
		}
		
		sum = sum / 2;
		
		return sum / (graph.getNumEdges());
	}
	
	/**
	 * Retorna uma string representando o grafo.
	 * @param graph
	 * Recebe um grafo
	 * @param type
	 *  Recebe o tipo de representaçao a ser feita.
	 */
	@Override
	public String graphRepresentation(Graph graph, String type) {
		return graph.graphRepresentation(type);
	}
	
	/** Faz uma busca em largura no grafo.
	 * @param graph
	 * Recebe um grafo
	 * @param v
	 *  Recebe o vertice inicial
	 */
	@Override
	public String BFS(Graph graph, int v) {
		String out = "";
		for (String line : graph.BFS(v)) {
			out += line;
		}
		return out;
	}
	
	/**
	 * Faz uma busca em profundidade no grafo.
	 * @param graph
	 * 	Recebe um grafo
	 * @param v
	 * 	Recebe o vertice inicial
	 */
	@Override
	public String DFS(Graph graph, int v) {
		String[] out = new String[graph.getNumVertex()];
		int[] levels = new int[graph.getNumVertex() + 1];
	    String[] res = graph.DFS(graph, v + 1, levels, out);
	    return String.join("\n", res);
	 }
	
	
	/** Retorna um booleano indicando se o grafo eh connectado.
	 * @param graph
	 * Recebe um grafo
	 */
	@Override
	public boolean connected(Graph graph) {
		return graph.connected();
	}
	
	/**
	 * Retorna uma string indicando o menor caminho entre dois vertices
	 * @param graph
	 * Recebe um grafo
	 * @param v1
	 * @param v2
	 * Recebe os dois vertices na qual o menor caminho sera calculado.
	 */
	@Override
	public String shortestPath(Graph graph, Node v1, Node v2) {
		return graph.shortestPath(v1, v2);
	}
	
	/** Encontra a arvore geradora minima de um grafo.
	 * @param graph
	 * Recebe um grafo
	 */
	@Override
	public String mst(Graph graph) {
		if (graph.getDefault_weigth() && graph.connected()) {
			Graph mst = graph.graphMST();
			int firstNode = mst.getAdjacencyList()[1].getIndex();
			return BFS(mst, firstNode);
		} else {
			return "Grafo não possui peso nas aresta ou não é conectado";
		}

	}
}

	
