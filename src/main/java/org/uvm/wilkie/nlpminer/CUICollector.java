package org.uvm.wilkie.nlpminer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CUICollector {
	private static final Pattern CUI_NUMBER_PATTERN = Pattern.compile("'C(\\d{7})'");
	HashMap<String, CUIItemset> itemsets = new HashMap<String, CUIItemset>();
	
	public CUICollector() {
	}
	
	public void put(List<String> cuiCollection, String pmid) {
		if (cuiCollection == null) {
			return;
		}
		CUIItemset newItemset = new CUIItemset(cuiCollection);
		if(itemsets.containsKey(newItemset.getKey())) {
			itemsets.get(newItemset.getKey()).matched(pmid);
		} else {
			itemsets.put(newItemset.getKey(), newItemset);
			newItemset.matched(pmid);
		}
	}
	
	public Collection<CUIItemset> getCuiItemsets() {
		return itemsets.values();
	}
	
	class CUIItemset {
		private List<Integer> pmids = new ArrayList<Integer>();
		private List<Integer> cuiNumbers = new ArrayList<Integer>();
		private int hashValue;
		private String key;
		
		public CUIItemset(List<String> cuis) {
			Matcher matcher;
			int intValue;
			hashValue = 1;
			key = "";
			for (String cuiString : cuis) {
				matcher = CUI_NUMBER_PATTERN.matcher(cuiString);
				if(matcher.matches()) {
					intValue = Integer.parseInt(matcher.group(1));
					if (!cuiNumbers.contains(intValue)) {
						cuiNumbers.add(intValue);
						hashValue *= (intValue * (intValue + 1));
					}
				} else {
					throw new RuntimeException("Could not parse: " + cuiString + " as CUI");
				}
			}
			Collections.sort(cuiNumbers);
			
			StringBuilder keyBuilder = new StringBuilder(cuiNumbers.size() * 7);
			for (int cui : cuiNumbers) {
				keyBuilder.append(cui);
			}
			key = keyBuilder.toString();
		}
		
		public void matched(String pmid) {
			pmids.add(Integer.parseInt(pmid));
		}
		
		public int getNumber() {
			return pmids.size();
		}
		
		public String getKey() {
			return key;
		}
		
		public List<Integer> getCuiNumbers() {
			return cuiNumbers;
		}
		
		@Override
		public boolean equals(Object arg0) {
			try {
				CUIItemset o2 = (CUIItemset)arg0;
				
				return this.getKey().equals(o2.getKey());
//				List<Integer> cuis1 = getCuiNumbers();
//				List<Integer> cuis2 = o2.getCuiNumbers();
//				
//				if (cuis1.size() != cuis2.size()) {
//					return false;
//				}
//				
//				for (int i = 0; i < cuis1.size(); i++) {
//					if (cuis1.get(i) != cuis2.get(i)) {
//						return false;
//					}
//				}
//				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		@Override
		public String toString() {
			return getNumber() + " matches for CUIDs: " + getCuiNumbers().toString();
		}
		
		@Override
		public int hashCode() {
			return hashValue;
		}
	}
}
