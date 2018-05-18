package tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;

import org.junit.Test;

import entity.Graph;
import entity.Node;
import junit.framework.Assert;
import library.GraphsLibrary;

public class GraphsLibraryTest {

	private GraphsLibrary graphLibrary;
	private Graph basicGraph;
	private Graph desconnectedGraph;
	private Graph weightedGraph;
	
	private static final String NL = System.lineSeparator();
	
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
		
		String typeAM = graphLibrary.graphRepresentation(g1, "AM");
		
		String typeALExpec ="1 - 2(0.1) 5(1.0) " + NL + 
							"2 - 1(0.1) 5(0.2) " + NL + 
							"3 - 5(5.0) 4(-9.5) " + NL + 
							"4 - 3(-9.5) 5(2.3) " + NL + 
							"5 - 2(0.2) 3(5.0) 4(2.3) 1(1.0) " + NL;

		
		assertEquals(typeALExpec, typeAL);
		
		String typeAMExpec =" 0 1 2 3 4 5" + NL + 
							" 1 0 0.1 0 0 1.0" + NL + 
							" 2 0.1 0 0 0 0.2" + NL + 
							" 3 0 0 0 -9.5 5.0" + NL + 
							" 4 0 0 -9.5 0 2.3" + NL + 
							" 5 1.0 0.2 5.0 2.3 0" + NL;
		
		assertEquals(typeAMExpec, typeAM);
		
		
		GraphsLibrary graph2 = new GraphsLibrary();
		Graph g2 = graph2.readGraph("weightedGraph.txt");
		
		
		
		String typeAL1 = graph2.graphRepresentation(g2, "AL");
		
		String typeAM1= graph2.graphRepresentation(g2, "AM");
		
		String typeAL1Expec ="1 - 2 5 " + NL + 
							"2 - 1 5 " + NL + 
							"3 - 5 4 " + NL + 
							"4 - 3 5 " + NL + 
							"5 - 2 3 4 1 " + NL;
		
		assertEquals(typeAL1Expec, typeAL1);
		
		String typeAM1Expec=" 0 1 2 3 4 5" + NL + 
							" 1 0 1 0 0 1" + NL + 
							" 2 1 0 0 0 1" + NL + 
							" 3 0 0 0 1 1" + NL + 
							" 4 0 0 1 0 1" + NL + 
							" 5 1 1 1 1 0" + NL;
		
		assertEquals(typeAM1Expec, typeAM1);
		
	}
	@Test
	public void testMST() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		weightedGraph = graphLibrary.readWeightedGraph("graph.txt");
		
		String bfsMST = graphLibrary.mst(weightedGraph);
		String bfsMSTexpec ="1 - 0 -" + NL + 
							"2 - 1 1" + NL + 
							"3 - 4 4" + NL + 
							"4 - 3 5" + NL + 
							"5 - 2 2" + NL;
		assertEquals(bfsMSTexpec, bfsMST);
		
		GraphsLibrary graph2 = new GraphsLibrary();
		Graph g2 = graph2.readWeightedGraph("weightedGraph.txt");
		
		String g2MST = graph2.mst(g2);
		String g2MSTExpec = "1 - 0 -" + NL + 
							"2 - 1 1" + NL + 
							"3 - 2 5" + NL + 
							"4 - 2 5" + NL + 
							"5 - 1 1" + NL;
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
