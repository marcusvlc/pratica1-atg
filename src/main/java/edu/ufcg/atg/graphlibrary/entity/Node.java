package edu.ufcg.atg.graphlibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int index;
	private List<Edge> edges;
	public boolean visited;
	public String label;

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

	public void addAdjacentNode(int nodeIndex,int nodeIndexPre) {
		Edge newEdge = new Edge(nodeIndex, nodeIndexPre);
		this.edges.add(newEdge);		
	}

	public void addAdjacentNode(int nodeIndex , double weight, int nodeIndexPre) {
		Edge newEdge = new Edge(nodeIndex, weight, nodeIndexPre);
		this.edges.add(newEdge);		
	}

}