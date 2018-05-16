package entity;

public class Edge {
	
	private int nodeIndex;
	private double weight;
	
    private final static int DEFAULT_NODE_WEIGTH = 1;
    
    public Edge(int nodeIndex) {
    	this.nodeIndex = nodeIndex;
        this.weight = DEFAULT_NODE_WEIGTH;
    }
    
    public Edge (int nodeIndex, double weight){
    	this.nodeIndex = nodeIndex;
        this.weight = weight;
    }

	public int getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(int nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
