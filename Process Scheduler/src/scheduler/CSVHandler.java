package scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

	public List<Process> loadCSV(String filename) {
		
		Path pathToFile = Paths.get(filename);

		List<Process> allProcesses = new ArrayList<>();
		
		// Open the file
		try(BufferedReader br = Files.newBufferedReader(pathToFile)){
			
			// Read the first line
			String line = br.readLine();
			
			// Loop through csv file
			while (line != null) {
				
				// Split into array
				String[] processParams = line.split(",");
				
				// Add a new process to the list
				allProcesses.add(new Process(processParams));
				
				// Read in the next line
				line = br.readLine();
				
			}
			
		} catch(IOException io) {
			io.printStackTrace();
		}
		
		return allProcesses;
	}
	
}
