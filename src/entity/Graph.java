package entity;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	private Node[] adjacencyList;

	public Graph(int numVertex) {
		this.adjacencyList = new Node[numVertex + 1];
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
		return numEdges;
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
		fromNode.addAdjacentNode(toNode);
	}

	public String[] BFS(int index) {

		String[] out = new String[this.getNumVertex()];
		int[] levels = new int[this.getNumVertex() + 1];
		LinkedList<Integer> queue = new LinkedList<>();

		out[0] = index + " - 0 -\n";
		levels[index] = 1;
		queue.add(index);

		while (!queue.isEmpty()) {
			for (Node node : this.adjacencyList[index].getAdjacentNodes()) {
				if (levels[node.getIndex()] == 0) {
					levels[node.getIndex()] = levels[index] + 1;
					queue.add(node.getIndex());
					out[node.getIndex() - 1] = node.getIndex() + " - " + (levels[node.getIndex()] - 1) + " " + index
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
			List<Node> node = this.adjacencyList[i].getAdjacentNodes();

			for (Node nodeAux : node) {
				if (nodeAux.getWeight() != 0.0) {
					graph += Integer.toString(nodeAux.getIndex()) + "(" + Double.toString(nodeAux.getWeight()) + ")"
							+ " ";
				} else {
					graph += Integer.toString(nodeAux.getIndex()) + " ";
				}
			}

			graph += System.lineSeparator();

		}
		
		return graph;
	}

	private void graphRepresentationAM(String[][] graphRep) {

		for (int i = 1; i < graphRep.length; i++) {

			List<Node> node = this.adjacencyList[i].getAdjacentNodes();

			for (Node nodeAux : node) {
				int indexLine = nodeAux.getIndex();

				if (nodeAux.getWeight() != 0.0) {

					graphRep[i][indexLine] = Double.toString(nodeAux.getWeight());

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
}
