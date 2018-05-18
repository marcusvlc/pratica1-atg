package entity;

public class Edge implements Comparable<Edge>{
	
	private int nodeIndex;
	private int nodeIndexPre;
	private double weight;
	
    public final static int DEFAULT_NODE_WEIGTH = 1;
    
    public Edge(int nodeIndex, int nodeIndexPre) {
    	this(nodeIndex, DEFAULT_NODE_WEIGTH, nodeIndexPre);
    }
    
    public Edge (int nodeIndex, double weight, int nodeIndexPre){
    	this.nodeIndex = nodeIndex;
        this.weight = weight;
        this.nodeIndexPre = nodeIndexPre;
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

	public int getNodeIndexPre() {
		return nodeIndexPre;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.weight < o.weight) {
			return -1;
		}
		if(this.weight > o.weight) {
			return 1;
		}
		return 0;
	}
	
	
	

}