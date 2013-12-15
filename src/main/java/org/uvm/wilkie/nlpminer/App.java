package org.uvm.wilkie.nlpminer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.uvm.wilkie.nlpminer.CUICollector.CUIItemset;

import ca.pfv.spmf.patterns.itemset_set_integers_with_tids.Itemsets;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final File FOLDER_TO_ITERATE = new File("/home/nwilkie/Downloads/MetaMap2012Baseline/singleDownload_2012");
//	public static final File FOLDER_TO_ITERATE = new File("/home/nwilkie/Downloads/MetaMap2012Baseline/miniset");
	public static final File PMID_INPUT_FILE = new File("/home/nwilkie/workspaceQC/pubmed_results.txt");
	public static final File TRANSACTION_OUTPUT_FILE_1 = new File("/home/nwilkie/workspaceQC/transactions1.txt");
	public static final File TRANSACTION_OUTPUT_FILE_2 = new File("/home/nwilkie/workspaceQC/transactions2.txt");
	public static final File COLLECTOR_XML_OUTPUT_FILE = new File("/home/nwilkie/workspaceQC/collector.xml");
	
    public static void main( String[] args ) throws IOException
    {
		iterate();
    }
    
    public static void iterate() throws IOException {
    	long startTime = System.currentTimeMillis();
    	boolean exception = false;
    	MMOFileScanner fileScanner = null;
    	try {
	    	fileScanner = new MMOFileScanner();
	    	PMIDList pmidList = new PMIDList(PMID_INPUT_FILE);
	    	File[] files = FOLDER_TO_ITERATE.listFiles();
	    	Arrays.sort(files);
	    	for (int i = 0; i < files.length; i++) {
	    		File f = files[i];
	    		System.out.println("Processing file " + i + "/" + files.length + ": " + f.getName() + "(" + f.length() / 1048576L + " MB)");
	    		fileScanner.scan(f, TRANSACTION_OUTPUT_FILE_1, pmidList);
	    	}
	    	
	    	System.out.println("Saving SPMF transaction output file");
	    	fileScanner.getCollector().saveSpmfTransactionFile(TRANSACTION_OUTPUT_FILE_2);
	    	
	    	System.out.println("Saving XML CUICollector representation");
	    	fileScanner.getCollector().saveXml(COLLECTOR_XML_OUTPUT_FILE);
	        
	        System.out.println("Beginning analysis");
	        TransactionAnalysis transactionAnalysis = new TransactionAnalysis(fileScanner.getCollector());
	        Itemsets charmItemsets = transactionAnalysis.runCharm();
	        
	        charmItemsets.printItemsets(transactionAnalysis.itemsetCount);
    	} catch (Exception e) {
    		e.printStackTrace();
    		exception = true;
    	} finally {
    		System.out.println("Process completed. Time: " + (System.currentTimeMillis() - startTime)/1000 + " seconds");
    		if (fileScanner != null) {
    			Collection<CUIItemset> cuiItemsets = fileScanner.getCollector().getCuiItemsets();
    			System.out.println("Itemset results (" + cuiItemsets.size() + "): " + cuiItemsets.toString());
    		}
    		if(exception) {
    			System.out.println("***EXCEPTION ENCOUNTERED***");
    		}
    	}
    }
}
