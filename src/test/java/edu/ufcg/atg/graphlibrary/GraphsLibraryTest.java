package edu.ufcg.atg.graphlibrary;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import edu.ufcg.atg.graphlibrary.entity.Graph;
import edu.ufcg.atg.graphlibrary.entity.Node;
import edu.ufcg.atg.graphlibrary.library.GraphsLibrary;

public class GraphsLibraryTest {

	private GraphsLibrary graphLibrary;
	private Graph basicGraph;
	private Graph desconnectedGraph;
	private Graph weightedGraph;
	private Graph weightedGraph2;
	
	private static final String NL = System.lineSeparator();
	
	@Before
	public void init() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		basicGraph = graphLibrary.readGraph("src/test/res/graph.txt");
		weightedGraph = graphLibrary.readWeightedGraph("src/test/res/graph.txt");
		weightedGraph2 = graphLibrary.readWeightedGraph("src/test/res/weightedGraph.txt");
		desconnectedGraph = graphLibrary.readGraph("src/test/res/desconnectedGraph.txt");
	}
	
	@Test
	public void testConnected() {
		assertEquals(true, graphLibrary.connected(basicGraph));
		assertEquals(false, graphLibrary.connected(desconnectedGraph));
	}
	
	@Test
	public void testVertexNumber() throws FileNotFoundException {
		assertEquals(5, graphLibrary.getVertexNumber(basicGraph));
		assertEquals(5, graphLibrary.getVertexNumber(desconnectedGraph));
		
	}
	
	@Test
	public void testEdgeNumber() throws FileNotFoundException {
		assertEquals(6, graphLibrary.getEdgeNumber(basicGraph));
		assertEquals(5, graphLibrary.getEdgeNumber(desconnectedGraph));

	}
	
	@Test
	public void testMeanEdge() throws FileNotFoundException {
		assertEquals(-0.15000004, graphLibrary.getMeanEdge(weightedGraph), 0.00000001);
	}
	
	@Test
	public void testGraphRepresentation() throws FileNotFoundException {
		String typeAL = graphLibrary.graphRepresentation(weightedGraph, "AL");
		String typeAM = graphLibrary.graphRepresentation(weightedGraph, "AM");
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
		Graph g2 = graph2.readGraph("src/test/res/weightedGraph.txt");
		
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
		String bfsMST = graphLibrary.mst(weightedGraph);
		String bfsMSTexpec ="1 - 0 -" + NL + 
							"2 - 1 1" + NL + 
							"3 - 4 4" + NL + 
							"4 - 3 5" + NL + 
							"5 - 2 2" + NL;
		assertEquals(bfsMSTexpec, bfsMST);		
		String g2MST = graphLibrary.mst(weightedGraph2);
		String g2MSTExpec = "1 - 0 -" + NL + 
							"2 - 1 1" + NL + 
							"3 - 2 5" + NL + 
							"4 - 2 5" + NL + 
							"5 - 1 1" + NL;
		assertEquals(g2MSTExpec, g2MST);
	}
	
	@Test
	public void testShortestPath() throws FileNotFoundException {
		Node[] nodes = weightedGraph2.getAdjacencyList();
		assertEquals("1 5 3", graphLibrary.shortestPath(weightedGraph2, nodes[1], nodes[3]));
		assertEquals("1 5 4", graphLibrary.shortestPath(weightedGraph2, nodes[1], nodes[4]));
		assertEquals("1 2", graphLibrary.shortestPath(weightedGraph2, nodes[1], nodes[2]));
		
	}

}
