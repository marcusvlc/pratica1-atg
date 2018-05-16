package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import entity.Graph;
import library.GraphsLibrary;

class GraphsLibraryTests {

	private GraphsLibrary graphLibrary;
	private Graph basicGraph;
	private Graph desconnectedGraph;
	
	@Test
	public void testConnected() throws FileNotFoundException {
		graphLibrary = new GraphsLibrary();
		basicGraph = graphLibrary.readGraph("graph.txt");
		desconnectedGraph = graphLibrary.readGraph("desconnectedGraph.txt");
		
		assertEquals(true, graphLibrary.connected(basicGraph));
		assertEquals(false, graphLibrary.connected(desconnectedGraph));
		
	}
	

}
