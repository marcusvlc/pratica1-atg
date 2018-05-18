package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Edge;
import entity.Graph;
import entity.Node;
import junit.framework.Assert;
import library.GraphsLibrary;

class GraphsLibraryTest {

	private GraphsLibrary graphLibrary;
	private Graph basicGraph;
	private Graph desconnectedGraph;
	private Graph weightedGraph;
	
	@Before
	public void init() {
		 graphLibrary = new GraphsLibrary();
		
	}
	
	@Test
	public void testConnected() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		basicGraph = graphLibrary.readGraph("graph.txt");
		desconnectedGraph = graphLibrary.readGraph("desconnectedGraph.txt");
		
		assertEquals(true, graphLibrary.connected(basicGraph));
		assertEquals(false, graphLibrary.connected(desconnectedGraph));
		
	}
	
	@Test
	public void testVertexNumber() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		Graph g1 = graphLibrary.readWeightedGraph("graph.txt");
		Graph g2 = graphLibrary.readGraph("desconnectedGraph.txt");
		
		assertEquals(5, graphLibrary.getVertexNumber(g1));
		assertEquals(5, graphLibrary.getVertexNumber(g2));
		
	}
	
	@Test
	public void testEdgeNumber() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		Graph g1 = graphLibrary.readWeightedGraph("graph.txt");
		Graph g2 = graphLibrary.readGraph("desconnectedGraph.txt");
		
		assertEquals(6, graphLibrary.getEdgeNumber(g1));
		assertEquals(5, graphLibrary.getEdgeNumber(g2));

	}
	
	@Test
	public void testMeanEdge() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		Graph g1 = graphLibrary.readWeightedGraph("graph.txt");
		
		Assert.assertEquals(-0.15000004, graphLibrary.getMeanEdge(g1), 0.00000001);
	}
	
	@Test
	public void testGraphRepresentation() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		Graph g1 = graphLibrary.readWeightedGraph("graph.txt");
		
		String typeAL = graphLibrary.graphRepresentation(g1, "AL");
		
		
		
		
	}
	@Test
	public void testMST() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		weightedGraph = graphLibrary.readWeightedGraph("graph.txt");
		
		String bfsMST = graphLibrary.mst(weightedGraph);
		String bfsMSTexpec ="1 - 0 -\n" + 
							"2 - 1 1\n" + 
							"3 - 4 4\n" + 
							"4 - 3 5\n" + 
							"5 - 2 2\n";
		assertEquals(bfsMSTexpec, bfsMST);
		
		GraphsLibrary graph2 = new GraphsLibrary();
		Graph g2 = graph2.readWeightedGraph("weightedGraph.txt");
		
		String g2MST = graph2.mst(g2);
		String g2MSTExpec = "1 - 0 -\n" + 
							"2 - 1 1\n" + 
							"3 - 2 5\n" + 
							"4 - 2 5\n" + 
							"5 - 1 1\n";
		assertEquals(g2MSTExpec, g2MST);
	}
	
	@Test
	public void testShortestPath() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		weightedGraph = graphLibrary.readGraph("weightedGraph.txt");
		Node[] nodes = weightedGraph.getAdjacencyList();
		
		assertEquals("1 5 3", graphLibrary.shortestPath(weightedGraph, nodes[1], nodes[3]));
		assertEquals("1 5 4", graphLibrary.shortestPath(weightedGraph, nodes[1], nodes[4]));
		assertEquals("1 2", graphLibrary.shortestPath(weightedGraph, nodes[1], nodes[2]));
		
	}

}
