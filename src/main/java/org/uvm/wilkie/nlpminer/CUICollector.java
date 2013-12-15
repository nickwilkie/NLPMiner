package org.uvm.wilkie.nlpminer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace="org.uvm.wilkie.nlpminer")
@XmlType(propOrder={"itemsets","emptyRecords"})
@XmlAccessorType(XmlAccessType.FIELD)
public class CUICollector {
	private static final Pattern CUI_NUMBER_PATTERN = Pattern.compile("'C(\\d{7})'");
	@XmlElementWrapper(name="itemsetList")
	@XmlElement(name="itemset")
	HashMap<String, CUIItemset> itemsets = new HashMap<String, CUIItemset>();
	int emptyRecords = 0;
	
	public CUICollector() {
	}
	
	public void emptyRecordFound() {
		emptyRecords++;
	}
	
	public int getEmptyRecords() {
		return emptyRecords;
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
		List<CUIItemset> result = new ArrayList<CUIItemset>(itemsets.values());
		Collections.sort(result, new Comparator<CUIItemset>(){
			@Override
			public int compare(CUIItemset arg0, CUIItemset arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0.getNumber(), arg1.getNumber()) * -1;
			}
		});
		return result;
	}
	
	public void saveXml(File outputFile) throws IOException {
		BufferedWriter writer = null;
		try {
			// we create an object for writing the output file
			outputFile.delete();
			outputFile.createNewFile();
			// we read the file line by line until the end of the file

		    JAXBContext context = JAXBContext.newInstance(CUICollector.class);
		    Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		    // Write to System.out
//		    m.marshal(this, System.out);

		    // Write to File
		    m.marshal(this, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	public static CUICollector loadFromXml(File xml) throws JAXBException, IOException {
	    JAXBContext context = JAXBContext.newInstance(CUICollector.class);
	    Unmarshaller um = context.createUnmarshaller();
	    return (CUICollector) um.unmarshal(new FileReader(xml));
	}
	
	public void saveSpmfTransactionFile(File outputFile) throws IOException {
		BufferedWriter writer = null;
		try {
			// we create an object for writing the output file
			outputFile.delete();
			outputFile.createNewFile();
			writer = new BufferedWriter(new FileWriter(outputFile,false)); 
			// we read the file line by line until the end of the file
			List<CUIItemset> cuiItemsets = new ArrayList<CUIItemset>(itemsets.values());
			for (int i = 0; i < cuiItemsets.size(); i++) {
				CUIItemset itemset = cuiItemsets.get(i);
				if (itemset.getCuiNumbers().size() == 0) {
					continue;
				}

				if (i != 0) {
					writer.newLine(); // create new line
				}
				// we use a set to store the values to avoid duplicates
				// because they are not allowed in a transaction
				Set<Integer> values = new HashSet<Integer>();
				for(Integer intToAdd : itemset.getCuiNumbers()){
					values.add(intToAdd);
				}
				
				// sort the transaction in lexical order
				List<Integer> listValues = new ArrayList<Integer>(values);
				Collections.sort(listValues);
				
				// for each item, we will output them
				for (int j=0; j<listValues.size(); j++) {
					if (j != 0) {
						writer.write(' ');
					}
					writer.write(listValues.get(j).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	@XmlRootElement(name="itemset")
	@XmlAccessorType(XmlAccessType.FIELD)
	static class CUIItemset {
		@XmlElementWrapper(name="pmids")
		@XmlElement(name="pmid")
		private List<Integer> pmids = new ArrayList<Integer>();
		
		@XmlElementWrapper(name="cuiNumbers")
		@XmlElement(name="cuiNumber")
		private List<Integer> cuiNumbers = new ArrayList<Integer>();
		
		private int hashValue;
		private String key;
		
		public CUIItemset() {
		}
		
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
			return getNumber() + "x " + getCuiNumbers().toString();
		}
		
		@Override
		public int hashCode() {
			return hashValue;
		}
	}
}
