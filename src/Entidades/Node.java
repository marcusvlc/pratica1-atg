package Entidades;

import java.util.ArrayList;

public class Node<Integer> {
	protected int data;
	protected ArrayList<Integer> nos;

	
	public Node(int data) {
		this.data = data;
		this.nos = new ArrayList<Integer>();
	}
	@Override
	public String toString() {
		return "" +data;
	}
	public int getData() {
		return data;
	}
	
	public void setNos(Integer noh) {
		this.nos.add(noh);
	}

}
