package org.uvm.wilkie.nlpminer;

import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.eclat_and_charm.AlgoCharm;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.patterns.itemset_set_integers_with_tids.Itemsets;

public class TransactionAnalysis {
	TransactionDatabase transactionDb;
	CUICollector collector;
	int itemsetCount = 0;
	
	public TransactionAnalysis(CUICollector collector) {
		this.collector = collector;
		generateTransactionDatabase();
	}
	
	public void generateTransactionDatabase() {
		System.out.println("Beginning generation of transaction database.");
		transactionDb = new TransactionDatabase();
		
		for(CUICollector.CUIItemset cuiItemset : collector.getCuiItemsets()) {
			for (int i = 0; i < cuiItemset.getNumber(); i++) {
				transactionDb.addTransaction(cuiItemset.getCuiNumbers());
				itemsetCount++;
			}
		}
	}
	
	public Itemsets runCharm() {
		System.out.println("Running CHARM algorithm on transaction database.");
		AlgoCharm charm = new AlgoCharm();
		
		try {
			Itemsets result = charm.runAlgorithmWithRelativeMinsup(null, transactionDb, false, 500, 3);
			
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
