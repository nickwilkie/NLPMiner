package org.uvm.wilkie.nlpminer;

import java.io.File;
import java.io.IOException;

import ca.pfv.spmf.patterns.itemset_set_integers_with_tids.Itemsets;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final File FOLDER_TO_ITERATE = new File("");
	public static final File OUTPUT_FILE = new File("");
	
    public static void main( String[] args ) throws IOException
    {
		File inputFile = new File(
				"/Users/Nick/Downloads/text.out_683.gz");
		File outputFile = new File(inputFile.getParent(), "processed-" + inputFile.getName() + ".txt.gz");
    	PMIDList pmidList = new PMIDList();
        MMOFileScanner scanner = new MMOFileScanner();
        scanner.scan(inputFile, outputFile, pmidList);
        
        System.out.println("Beginning analysis");
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis(scanner.getCollector());
        Itemsets charmItemsets = transactionAnalysis.runCharm();
        
        charmItemsets.printItemsets(transactionAnalysis.itemsetCount);
    }
    
    public static void iterate() throws IOException {
    	MMOFileScanner fileScanner = new MMOFileScanner();
    	PMIDList pmidList = new PMIDList();
    	for (File f : FOLDER_TO_ITERATE.listFiles()) {
    		fileScanner.scan(f, OUTPUT_FILE, pmidList);
    	}
    }
}
