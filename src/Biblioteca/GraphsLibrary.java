package Biblioteca;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import Entidades.Grafo;
import Interfaces.GraphsMethods;

public class GraphsLibrary implements GraphsMethods {
	
	private static final int NUM_VERTICES_POS = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		GraphsLibrary gl = new GraphsLibrary();
		gl.readGraph("graph.txt");
	}
	
	/** Metodo que le um arquivo e passa seus elementos para um array
	 * 
	 * @param elementosProGrafo 
	 * 		Array que ira guardar os elementos que irao formar o grafo
	 * @param path
	 * 		Caminho no qual se encontra o arquivo com o grafo
	 * @throws FileNotFoundException
	 */
	private void readGraphFile(ArrayList<String> elementosProGrafo, String path) throws FileNotFoundException {
		try {
			FileInputStream file = new FileInputStream(path);
			InputStreamReader is = new InputStreamReader(file);
			BufferedReader br = new BufferedReader(is);
			String line = new String();
			
			do {
				line = br.readLine();
				if (line != null ) {
					elementosProGrafo.add(line);
				}
				
			}while(line != null);
			
			
		} catch(Exception e) {
			throw new FileNotFoundException("Arquivo do grafo nao foi encontrado!");
		}
	}
	
	@Override
	public void readGraph(String path)  {
		ArrayList<String> elementosDoGrafo = new ArrayList<String>();


		try {
			readGraphFile(elementosDoGrafo, path);
			int numeroDeVertices = Integer.parseInt(elementosDoGrafo.get(NUM_VERTICES_POS));
			Grafo grafo = new Grafo(numeroDeVertices);
			
			// faltando construir o grafo.
			
			System.out.println(Arrays.toString(elementosDoGrafo.toArray())); // DEBUG
			System.out.println(grafo.getNumVertices()); // DEBUG
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Algo deu errado na leitura do arquivo que contem o grafo");
		}
		
		
		
	}

	@Override
	public void readWeightedGraph(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getVertexNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEdgeNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMeanEdge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String graphRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String BFS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String DFS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String SCC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String shortestPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mst() {
		// TODO Auto-generated method stub
		return null;
	}

}
