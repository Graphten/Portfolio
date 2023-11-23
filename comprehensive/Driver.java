package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the driver class that reads a file and creates a disjoint set based on the content of the file.
 * In addition, the driver performs unions and verifies whether two vertices are connected.
 * 
 * @author Dillon Boone and Frederico Tremonti
 * @version April 20th, 2023
 *
 */
public class Driver {
	public static void main (String[] args) {
		File inputFile = new File(args[0]);
		DisjointSetForest<String> vertexSetForest = new DisjointSetForest<>();
		
		try {
			Scanner scn = new Scanner(inputFile);
			
			// Add vertices to disjoint set
			while(scn.hasNextLine()) {
				String item = scn.nextLine();
				
				if(item.contentEquals(""))
					break;
				
				vertexSetForest.makeSet(item);
			}
			
			// Union vertices in disjoint set
			while(scn.hasNextLine()) {
				String vertices = scn.nextLine();
				
				if(vertices.contentEquals(""))
					break;
				
				String[] unionPair = vertices.split(" ");
				vertexSetForest.union(unionPair[0], unionPair[1]);
			}
			
			// Compare vertices to find connections
			while(scn.hasNextLine()) {
				String vertices = scn.nextLine();
				
				String[] comparisonPair = vertices.split(" ");
				boolean sameSet = vertexSetForest.getRepresentative(comparisonPair[0]).equals(vertexSetForest.getRepresentative(comparisonPair[1]));
				if(sameSet)
					System.out.println("connected");
				else
					System.out.println("not connected");
			}
			
			scn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
