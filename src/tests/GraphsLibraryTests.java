package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import entity.Graph;
import junit.framework.Assert;
import library.GraphsLibrary;

class GraphsLibraryTests {

	private GraphsLibrary graphLibrary;
	private Graph basicGraph;
	private Graph desconnectedGraph;
	
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
	
	
	
	
	
	
	

}
