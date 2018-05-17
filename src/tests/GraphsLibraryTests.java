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
	
	@Test
	public void testGraphRepresentation() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		Graph g1 = graphLibrary.readWeightedGraph("graph.txt");
		GraphsLibrary graphsLibrary2 = new GraphsLibrary();
		Graph g2 = graphsLibrary2.readGraph("graph.txt");
		
		
		String typeALWeight = graphLibrary.graphRepresentation(g1, "AL");
		String typeALWeightExpec = "1 - 2(0.1) 5(1.0) \r\n" + 
									"2 - 1(0.1) 5(0.2) \r\n" + 
									"3 - 5(5.0) 4(-9.5) \r\n" + 
									"4 - 3(-9.5) 5(2.3) \r\n" + 
									"5 - 2(0.2) 3(5.0) 4(2.3) 1(1.0) \r\n" + 
																		"";
		assertEquals(typeALWeightExpec, typeALWeight);
		
		String typeAMWeight = graphLibrary.graphRepresentation(g1, "AM");
		String typeAMWeightExpec =	" 0 1 2 3 4 5\r\n" + 
									" 1 0 0.1 0 0 1.0\r\n" + 
									" 2 0.1 0 0 0 0.2\r\n" + 
									" 3 0 0 0 -9.5 5.0\r\n" + 
									" 4 0 0 -9.5 0 2.3\r\n" + 
									" 5 1.0 0.2 5.0 2.3 0\r\n"; 
		assertEquals(typeAMWeightExpec, typeAMWeight);
		
		
		String typeAL = graphsLibrary2.graphRepresentation(g2, "AL");
		String typeALExpec ="1 - 2 5 \r\n" + 
							"2 - 1 5 \r\n" + 
							"3 - 5 4 \r\n" + 
							"4 - 3 5 \r\n" + 
							"5 - 2 3 4 1 \r\n";
		
		assertEquals(typeALExpec, typeAL);
		String typeAM = graphsLibrary2.graphRepresentation(g2, "AM");
		String typeAMExpec =" 0 1 2 3 4 5\r\n" + 
							" 1 0 1 0 0 1\r\n" + 
							" 2 1 0 0 0 1\r\n" + 
							" 3 0 0 0 1 1\r\n" + 
							" 4 0 0 1 0 1\r\n" + 
							" 5 1 1 1 1 0\r\n";
		
		assertEquals(typeAMExpec, typeAM);
	}
	
	
	
	
	
	
	

}
