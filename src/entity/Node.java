package entity;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int index;
	private List<Edge> edges;

	public Node(int index) {
		this.index = index;
		this.edges = new ArrayList<>();
	}

	public int getIndex() {
		return index;
	}

	public List<Edge> getAdjacentNodes() {
		return edges;
	}

	public void setAdjacentNodes(List<Edge> edges) {
		this.edges = edges;
	}

	public void addAdjacentNode(int nodeIndex) {
		Edge newEdge = new Edge(nodeIndex);
		this.edges.add(newEdge);		
	}

	public void addAdjacentNode(int nodeIndex , double weight) {
		Edge newEdge = new Edge(nodeIndex, weight);
		this.edges.add(newEdge);		
	}

}
