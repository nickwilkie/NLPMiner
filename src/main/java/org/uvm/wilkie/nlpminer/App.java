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
	public static final File TRANSACTION_OUTPUT_FILE_1 = new File("");
	public static final File PMID_INPUT_FILE = new File("/Users/Nick/Downloads/pubmed_result.txt");
	public static final File TRANSACTION_OUTPUT_FILE_2 = new File("");
	
    public static void main( String[] args ) throws IOException
    {
		File inputFile = new File(
				"/Users/Nick/Downloads/text.out_683.gz");
		File outputFile = new File(inputFile.getParent(), "processed-" + inputFile.getName() + ".txt.gz");
    	PMIDList pmidList = new PMIDList(PMID_INPUT_FILE);
        MMOFileScanner scanner = new MMOFileScanner();
        scanner.scan(inputFile, outputFile, pmidList);
        
        System.out.println("Beginning analysis");
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis(scanner.getCollector());
        Itemsets charmItemsets = transactionAnalysis.runCharm();
        
        charmItemsets.printItemsets(transactionAnalysis.itemsetCount);
    }
    
    public static void iterate() throws IOException {
    	MMOFileScanner fileScanner = new MMOFileScanner();
    	PMIDList pmidList = new PMIDList(PMID_INPUT_FILE);
    	for (File f : FOLDER_TO_ITERATE.listFiles()) {
    		fileScanner.scan(f, TRANSACTION_OUTPUT_FILE_1, pmidList);
    	}
    	
    	System.out.println("Saving SPMF transaction output file");
    	fileScanner.getCollector().saveSpmfTransactionFile(TRANSACTION_OUTPUT_FILE_2);
        
        System.out.println("Beginning analysis");
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis(fileScanner.getCollector());
        Itemsets charmItemsets = transactionAnalysis.runCharm();
        
        charmItemsets.printItemsets(transactionAnalysis.itemsetCount);
    }
}
