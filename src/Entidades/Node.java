package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private final static int DEFAULT_NODE_WEIGTH = 0;

	private int index;
	private double weight;
	private List<Node> adjacentNodes;

	public Node(int index, double weight) {
		this.index = index;
		this.adjacentNodes = new ArrayList<>();
		this.weight = weight;
	}

	public Node(int index) {
		this(index, DEFAULT_NODE_WEIGTH);
	}

	public int getIndex() {
		return index;
	}

	public List<Node> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(List<Node> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	public void addAdjacentNode(Node node) {
		this.adjacentNodes.add(node);
	}
}
