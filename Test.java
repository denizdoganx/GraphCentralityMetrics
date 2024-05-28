

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Test {
	//reading data from given files
	static boolean readFileAndPutGraph(String fileSource, Graph<Integer> graph) {
		//first vertex was added then they were connected with edge
		File file = new File(fileSource);
		BufferedReader bReader;
		String line; 
		String[] edgeInfo;
		int vertex1, vertex2;
		if(!file.exists()) {
			System.out.println("The file named \"" + fileSource + "\" could not be found");
			return false;
		}
		else {
			try {
				bReader = new BufferedReader(new FileReader(file));
				while((line = bReader.readLine()) != null) {
					edgeInfo = line.split(" ");
					vertex1 = Integer.valueOf(edgeInfo[0]);
					vertex2 = Integer.valueOf(edgeInfo[1]);
					graph.addVertex(vertex1);
					graph.addVertex(vertex2);
					graph.addEdge(vertex1, vertex2);
				}
				bReader.close();
			} catch (Exception e) {}
			return true;
		}
	}
	public static void main(String[] args) {
		System.out.println("2018510019 DENÝZ DOÐAN");
		final String facebook = "facebook_social_network.txt";
		Graph<Integer> facebookGraph = new Graph<>();
		
		final String karate = "karate_club_network.txt";
		Graph<Integer> karateGraph = new Graph<>();
		
		boolean facebookFlag = readFileAndPutGraph(facebook, facebookGraph);
		boolean karateFlag = readFileAndPutGraph(karate, karateGraph);
		
		if(karateFlag) {
			System.out.println("Zachary Karate Club Network\n");
			printBetweenessAndClosenessValues(karateGraph);
		}
		if(facebookFlag) {
			System.out.println("\nFacebook Social Network");
			System.out.println("Loading facebook social network data please wait...\n");
			printBetweenessAndClosenessValues(facebookGraph);
		}
	}
	static void printBetweenessAndClosenessValues(Graph<Integer> graph) {
		//The output screen is written in a method to avoid code duplication.
		graph.findBetweennessAndClosenessOfGraph();
		System.out.println("1. Betweenness Centrality");
		System.out.println("\tNode : " + graph.getBetweennessCentrality() + "\tValue : " + graph.getValueOfBetweenness());
		System.out.println("2. Closeness Centrality");
		System.out.println("\tNode : " + graph.getClosenessCentrality() + "\tValue : " + graph.getValueOfCloseness());
	}
}
