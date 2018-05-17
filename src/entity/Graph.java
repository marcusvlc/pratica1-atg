package entity;

import java.util.LinkedList;
import java.util.List;

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

	//Adiciona aresta sem peso (peso padrao eh 1)
	public void addEdge(int from, int to) {
		Node fromNode = this.addOrReturnVertex(from);
		Node toNode = this.addOrReturnVertex(to);
		fromNode.addAdjacentNode(toNode.getIndex());
		toNode.addAdjacentNode(fromNode.getIndex());
	}
	//Adiciona aresta com peso
	public void addEdge(int from, int to, double weight) {
		Node fromNode = this.addOrReturnVertex(from);
		Node toNode = this.addOrReturnVertex(to);
		fromNode.addAdjacentNode(toNode.getIndex(), weight);
		toNode.addAdjacentNode(fromNode.getIndex(), weight);
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
					out[edge.getNodeIndex() - 1] = edge.getNodeIndex() + " - " + (levels[edge.getNodeIndex()] - 1) + " " + index
							+ "\n";
				}
			}
			queue.removeFirst();
			if (!queue.isEmpty())
				index = queue.peek();
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
		}

		return graph;
	}

	private String graphRepresentationAL() {

		String graph = "";

		for (int i = 1; i < adjacencyList.length; i++) {

			graph += Integer.toString(adjacencyList[i].getIndex()) + " - ";
			List<Edge> edges = this.adjacencyList[i].getAdjacentNodes();

			for (Edge edgeAux : edges) {
				if (edgeAux.getWeight() != Edge.DEFAULT_NODE_WEIGTH) {
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

				if (edgeAux.getWeight() != 0.0) {

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
	
	public boolean connected() {
		Node auxNode = firstNotNullNode();
		
		if(auxNode != null) {
			LinkedList<Integer> queue =  new LinkedList<>();
			LinkedList<Integer> visitedNodes =  new LinkedList<>();

			queue.addFirst(auxNode.getIndex());
			
			int achievableNodes = 0;
			
			while(!queue.isEmpty()) {
				
				List<Edge> adjacentNodes = addOrReturnVertex(queue.getFirst()).getAdjacentNodes();
				
				for (int i = 0; i < adjacentNodes.size(); i++) {
					int index = adjacentNodes.get(i).getNodeIndex();
					if(!visitedNodes.contains(index)) {
						queue.addLast(index);
						visitedNodes.add(index);
						achievableNodes++;
					}
				}
				queue.removeFirst();
				
			}
			
			if(achievableNodes == this.getNumVertex())
				return true;
		}
		return false;
		
	}
	
	private Node firstNotNullNode() {
		Node node = null;
		
		int i = 0;
		while(node == null && i < this.adjacencyList.length) {
			if(this.adjacencyList[i] != null)
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
	
	

}
