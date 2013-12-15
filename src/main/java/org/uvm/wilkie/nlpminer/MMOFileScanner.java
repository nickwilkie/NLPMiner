package org.uvm.wilkie.nlpminer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.uvm.wilkie.nlpminer.CUICollector.CUIItemset;

public class MMOFileScanner {
	private CUICollector collector = null;
	
	Pattern pmidPattern = Pattern.compile("utterance\\('(\\d+)\\.");
	Pattern relevantData = Pattern
			.compile("(utterance|args|mappings)\\((.+)\\).\\n");
	Pattern evPattern = Pattern
			.compile("ev\\((.{1,5}),('.*?'),'?.*?'?,('?.*?'?),\\[.*?\\],(\\[[^\\]]*?\\]),\\[.*?\\],[yn].*\\d\\)");
	
	public MMOFileScanner() {
		setCollector(new CUICollector());
	}
	
	public void scan(File inputFile, File outputFile, PMIDList pmidList) {
		long startTime = System.currentTimeMillis();
		int count = 0;
		Scanner utteranceScanner = null;
		OutputStreamWriter writer = null;
		try {
			InputStream is = new BufferedInputStream(new GZIPInputStream(
					new FileInputStream(inputFile)));
			// "/Users/Nick/Documents/workspaceQC/nlppattern/resources/sampleMMO.txt")));
			// outputFile.delete();
			outputFile.createNewFile();
			writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(outputFile, true)));
			utteranceScanner = new Scanner(is);
			utteranceScanner.useDelimiter("'EOU'\\.");

			String utterance;
			Matcher PMIDMatcher;
			Matcher relevantDataMatcher;
			Matcher evPatternMatcher;
			String pmid;
			
			String previousPMID = null;
			ArrayList<String> collectingCuis = new ArrayList<String>();
			
			System.out.println("Matched and processed in this file: ");
			while (utteranceScanner.hasNext()) {
				utterance = utteranceScanner.next();
				// System.out.println("Line " + ++i);
				// System.out.println("\t" + utterance);

				PMIDMatcher = pmidPattern.matcher(utterance);
				if (PMIDMatcher.find()) {
					pmid = PMIDMatcher.group(1);
					if(!pmid.equals(previousPMID)) {
						if (collectingCuis.size() > 0) {
							getCollector().put(collectingCuis, pmid);
							for(String cui : collectingCuis) {
								writer.write(cui);
								writer.write(',');
							}
							writer.write('\n');
							
							collectingCuis.clear();
						} else {
							getCollector().emptyRecordFound();
						}
						
						previousPMID = pmid;
					}
					if (pmidList.isRelevantPMID(Integer.parseInt(pmid))) {
//						System.out.println(++count + "\tPMID: " + pmid);
						if (++count % 100 == 0) {
							System.out.print(count + "...");
						}

						relevantDataMatcher = relevantData.matcher(utterance);
						while (relevantDataMatcher.find()) {
							if (relevantDataMatcher.group(0).startsWith(
									"mapping")) {
								evPatternMatcher = evPattern
										.matcher(relevantDataMatcher.group(0));
								while (evPatternMatcher.find()) {
									if (evPatternMatcher.groupCount() == 4) {
										if(evPatternMatcher.group(4).contains("dsyn")) {
//											System.out.println("Name: "
//													+ evPatternMatcher.group(3));
//											System.out.println("CUID: "
//													+ evPatternMatcher.group(2));
//											System.out.println("SemanticTerm: "
//													+ evPatternMatcher.group(4));
											if (!collectingCuis.contains(evPatternMatcher.group(2))) {
												collectingCuis.add(evPatternMatcher.group(2));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			//write the last one!
			if(collectingCuis.size() > 0) {
				getCollector().put(collectingCuis, previousPMID);
				
				for(String cui : collectingCuis) {
					writer.write(cui);
					writer.write(',');
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (utteranceScanner != null) {
				utteranceScanner.close();
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
			System.out.println();
			System.out.println("File completed in "
					+ (System.currentTimeMillis() - startTime) + "ms");
			Collection<CUIItemset> cuiItemsets = getCollector().getCuiItemsets();
			System.out.println("Itemset results (" + cuiItemsets.size() + "): " + cuiItemsets.toString());
			System.out.println();
		}
	}

	public CUICollector getCollector() {
		return collector;
	}

	public void setCollector(CUICollector collector) {
		this.collector = collector;
	}
}
