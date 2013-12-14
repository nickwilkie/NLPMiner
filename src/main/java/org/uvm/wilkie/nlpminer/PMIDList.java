package org.uvm.wilkie.nlpminer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PMIDList {
	List<Integer> pmids = new ArrayList<Integer>();
	
	public PMIDList(File fileToLoad) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileToLoad));
		String line;
		while((line = reader.readLine()) != null && !line.trim().isEmpty()) {
			try {
				pmids.add(Integer.parseInt(line));
			} catch (NumberFormatException ex) {
			}
		}
		reader.close();
		Collections.sort(pmids); //necessary for binary search
	}
	
	public boolean isRelevantPMID(int pmid) {
		return Collections.binarySearch(pmids, pmid) >= 0;
	}
}
