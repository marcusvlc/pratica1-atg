package edu.ufcg.atg.graphlibrary.entity;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Graph {


	private boolean default_weigth;

	private Node[] adjacencyList;

	public Graph(int numVertex) {
		this.adjacencyList = new Node[numVertex + 1];
	}

	public Node[] getAdjacencyList() {
		return this.adjacencyList;
	}

	public int getNumVertex() {
		return this.adjacencyList.length - 1;
	}

	public int getNumEdges() {
		int numEdges = 0;
		for (Node node : this.adjacencyList) {
			if (node != null) {
				numEdges += node.getAdjacentNodes().size();
			}
		}
		return numEdges / 2;
	}

	public Node addOrReturnVertex(int index) {
		Node node = this.adjacencyList[index];
		if (node == null) {
			node = new Node(index);
			this.adjacencyList[index] = node;
		}
		return node;
	}

	public void addEdge(int from, int to) {
		Node fromNode = this.addOrReturnVertex(from);
		Node toNode = this.addOrReturnVertex(to);
		fromNode.addAdjacentNode(toNode.getIndex(), from);
		toNode.addAdjacentNode(fromNode.getIndex(), to);
	}

	public void addEdge(int from, int to, double weight) {
		Node fromNode = this.addOrReturnVertex(from);
		Node toNode = this.addOrReturnVertex(to);
		fromNode.addAdjacentNode(toNode.getIndex(), weight, from);
		toNode.addAdjacentNode(fromNode.getIndex(), weight, to);
	}

	public String[] BFS(int index) {

		String[] out = new String[this.getNumVertex()];
		int[] levels = new int[this.getNumVertex() + 1];
		LinkedList<Integer> queue = new LinkedList<>();

		out[0] = index + " - 0 -\n";
		levels[index] = 1;
		queue.add(index);

		while (!queue.isEmpty()) {
			for (Edge edge : this.adjacencyList[index].getAdjacentNodes()) {
				if (levels[edge.getNodeIndex()] == 0) {
					levels[edge.getNodeIndex()] = levels[index] + 1;
					queue.add(edge.getNodeIndex());
					out[edge.getNodeIndex() - 1] = edge.getNodeIndex() + " - " + (levels[edge.getNodeIndex()] - 1) + " "
							+ index + "\n";
				}
			}
			queue.removeFirst();
			if (!queue.isEmpty())
				index = queue.peek();
		}

		return out;
	}

	public String[] DFS(Graph graph, int index, int[] levels, String[] out) {
		for (Edge edge : (adjacencyList[index].getAdjacentNodes())) {
			if (levels[edge.getNodeIndex()] == 0) {
				out[edge.getNodeIndex()] = edge.getNodeIndex() + " ";
				levels[edge.getNodeIndex()] = levels[index] + 1;
				DFS(graph, edge.getNodeIndex(), levels, out);
			}
		}
		return out;

	}

	public String graphRepresentation(String type) {

		String graph = null;
		if (type.equals("AM")) {

			String[][] graphRep = new String[this.adjacencyList.length][this.adjacencyList.length];

			this.lineAddNumber(graphRep);
			this.columnAddNumber(graphRep);

			this.graphRepresentationAM(graphRep);

			graph = this.imprimirMatrix(graphRep);

		} else if (type.equals("AL")) {
			graph = this.graphRepresentationAL();
		} else {
			graph = "incompatible type";
		}

		return graph;
	}

	private String graphRepresentationAL() {

		String graph = "";

		for (int i = 1; i < adjacencyList.length; i++) {

			graph += Integer.toString(adjacencyList[i].getIndex()) + " - ";
			List<Edge> edges = this.adjacencyList[i].getAdjacentNodes();

			for (Edge edgeAux : edges) {
				if (this.default_weigth == true) {
					graph += Integer.toString(edgeAux.getNodeIndex()) + "(" + Double.toString(edgeAux.getWeight()) + ")"
							+ " ";
				} else {
					graph += Integer.toString(edgeAux.getNodeIndex()) + " ";
				}
			}

			graph += System.lineSeparator();

		}

		return graph;
	}

	private void graphRepresentationAM(String[][] graphRep) {

		for (int i = 1; i < graphRep.length; i++) {

			List<Edge> edges = this.adjacencyList[i].getAdjacentNodes();

			for (Edge edgeAux : edges) {
				int indexLine = edgeAux.getNodeIndex();

				if (this.default_weigth == true) {

					graphRep[i][indexLine] = Double.toString(edgeAux.getWeight());

				} else {

					graphRep[i][indexLine] = "1";

				}

			}

		}
	}

	private void lineAddNumber(String[][] graphMatrix) {
		for (int i = 1; i < graphMatrix.length; i++) {
			graphMatrix[i][0] = Integer.toString(this.adjacencyList[i].getIndex());
		}
	}

	private void columnAddNumber(String[][] graphMatrix) {
		for (int i = 1; i < graphMatrix.length; i++) {
			graphMatrix[0][i] = Integer.toString(this.adjacencyList[i].getIndex());
		}
	}

	private String imprimirMatrix(String[][] graphMatrix) {
		String graph = "";
		for (int i = 0; i < graphMatrix.length; i++) {

			for (int j = 0; j < graphMatrix[i].length; j++) {
				if (graphMatrix[0][0] == null)
					graph += " ";
				if (graphMatrix[i][j] != null) {
					graph += graphMatrix[i][j];
				} else {
					graph += "0";
				}

			}
			graph += System.lineSeparator();

		}
		return graph;
	}
	
	/**
	 * Retorna true se o grafo eh conexo, ou seja, existe um caminho entre qualquer par de nodes
	 * 
	 * @return Boolean true se eh conexo, false caso contrario
	 */
	public boolean connected() {
		Node auxNode = firstNotNullNode();

		if (auxNode != null) {
			LinkedList<Integer> queue = new LinkedList<>();
			LinkedList<Integer> visitedNodes = new LinkedList<>();

			queue.addFirst(auxNode.getIndex());

			int achievableNodes = 0;

			while (!queue.isEmpty()) {

				List<Edge> adjacentNodes = addOrReturnVertex(queue.getFirst()).getAdjacentNodes();

				for (int i = 0; i < adjacentNodes.size(); i++) {
					int index = adjacentNodes.get(i).getNodeIndex();
					if (!visitedNodes.contains(index)) {
						queue.addLast(index);
						visitedNodes.add(index);
						achievableNodes++;
					}
				}
				queue.removeFirst();

			}

			if (achievableNodes == this.getNumVertex())
				return true;
		}
		return false;

	}
	
	/**
	 * Metodo auxiliar para connected. Encontra o primeiro Node da adjacencyList
	 * 
	 * @return Node, primeiro node encontrado da adjacencyList
	 */
	private Node firstNotNullNode() {
		Node node = null;

		int i = 0;
		while (node == null && i < this.adjacencyList.length) {
			if (this.adjacencyList[i] != null)
				node = this.adjacencyList[i];
			i++;
		}

		return node;
	}

	public boolean getDefault_weigth() {
		return default_weigth;
	}

	public void setDefault_weigth(boolean default_weigth) {
		this.default_weigth = default_weigth;
	}
	
	/**
	 * Encontra o menor caminho entre dois nodes dados
	 * 
	 * @param Node "raiz", v1
	 * @param Node a ser encontrado atraves do menor caminho, v2
	 * 
	 * @return String contendo o menor caminho
	 */
	public String shortestPath(Node v1, Node v2) {
		Map<Node, Double> graphNodes = new HashMap<Node, Double>();
		Map<Integer, Double> nodeDistance = new HashMap<Integer, Double>();
		Map<Integer, Integer> nodeParent = new HashMap<Integer, Integer>();
		
		graphNodes.put(v1, 0.0);
		nodeParent.put(v1.getIndex(), null);
		
		for (int i = 0; i < adjacencyList.length; i++) {
			if (adjacencyList[i] != null && !(adjacencyList[i].getIndex() == v1.getIndex()))
				graphNodes.put(adjacencyList[i], null);	
		}
		
		while(!graphNodes.isEmpty()) {
			Node actualNode = extractMin(graphNodes);
			List<Edge> adjacentNodes = actualNode.getAdjacentNodes();
			nodeDistance.put(actualNode.getIndex(), graphNodes.get(actualNode));
			
			for (int i = 0; i < adjacentNodes.size(); i++) {
				Node adjacentNode = addOrReturnVertex(adjacentNodes.get(i).getNodeIndex());
				
				if(graphNodes.containsKey(adjacentNode)){
					double newWeight = nodeDistance.get(actualNode.getIndex()) + adjacentNodes.get(i).getWeight();
					
					if(graphNodes.get(adjacentNode) == null || newWeight < graphNodes.get(adjacentNode)) {
						graphNodes.put(adjacentNode, newWeight);
						nodeParent.put(adjacentNode.getIndex(), actualNode.getIndex());
					}
				}
			}
			
			graphNodes.remove(actualNode);
		}
		
		String shortestPath = v2.getIndex() + "";
		
		Node auxNode = v2;
		while(auxNode != v1) {
			int nodeIndex = nodeParent.get(auxNode.getIndex());
			shortestPath = nodeIndex + " " + shortestPath ;
			auxNode = addOrReturnVertex(nodeIndex);
		}
		
		return shortestPath;
	}
	
	/**
	 * Metodo auxiliar para o shortestPath que encontra o node com menor peso em um Map
	 * 
	 * @param Map<Node, Double> que contem os nodes e seu respectivos pesos 
	 * 
	 * @return Node, node com o menor peso do Map
	 */
	private Node extractMin(Map<Node, Double> graphNodes ) {
		double minValue = Double.MAX_VALUE;
		Node nodeIndex = null;

		for (int i = 0; i < adjacencyList.length; i++) {
			
			if(graphNodes.get(adjacencyList[i]) != null && graphNodes.get(adjacencyList[i]) < minValue) {
				minValue = graphNodes.get(adjacencyList[i]);
				nodeIndex = adjacencyList[i];
			}
		}	
		
		return nodeIndex;
	}

	private int find(int[] subSet, int v) {

		if (subSet[v] == 0)
			return v;
		return find(subSet, subSet[v]);

	}

	private void union(int[] subSet, int v1, int v2) {

		int v1_set = find(subSet, v1);
		int v2_set = find(subSet, v2);

		subSet[v1_set] = v2_set;

	}

	public Graph graphMST() {
		ArrayList<Edge> arvore = new ArrayList<>();
		ArrayList<Edge> arestas = listEdges();
		int[] subSet = new int[adjacencyList.length - 1];
		
		for (Edge aresta : arestas) {
			int v1 = find(subSet, aresta.getNodeIndexPre()-1);
			int v2 = find(subSet, aresta.getNodeIndex()-1);
			if(v1 != v2) {
				arvore.add(aresta);
				union(subSet, v1, v2);
			}
		}
		Graph graph = new Graph(getNumVertex());
		graph.setDefault_weigth(true);
		for (Edge edge : arvore) {
			graph.addEdge(edge.getNodeIndexPre(), edge.getNodeIndex(), edge.getWeight());
		}
		return graph;
	}

	private ArrayList<Edge> listEdges() {
		ArrayList<Edge> edges = new ArrayList<>();
		
		for (int i = 1; i < adjacencyList.length; i++) {
			List<Edge> edgesNode = adjacencyList[i].getAdjacentNodes();

			for (Edge edge : edgesNode) {

				edges.add(edge);

			}
		}
		Collections.sort(edges);
		return edges;
	}
	
}