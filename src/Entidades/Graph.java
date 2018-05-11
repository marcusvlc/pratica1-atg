package Entidades;

import java.util.LinkedList;

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

	public Node addOrReturnVertex (int index) {
		Node node = this.adjacencyList[index];
		if (node == null) {
			node = new Node(index);
			this.adjacencyList[index] = node;
		}
		return node;
	}

	public void addEdge (int from, int to) {
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
					out[node.getIndex() - 1] = node.getIndex() + " - " +  (levels[node.getIndex()] - 1) + " " + index + "\n";
				}
			}
			queue.removeFirst();
			if (!queue.isEmpty()) index = queue.peek();
		}

		return out;
	}
}
