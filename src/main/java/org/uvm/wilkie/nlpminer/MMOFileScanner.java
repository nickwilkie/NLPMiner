package org.uvm.wilkie.nlpminer;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
		Writer os = null;
		try {
			InputStream is = new BufferedInputStream(new GZIPInputStream(
					new FileInputStream(inputFile)));
			// "/Users/Nick/Documents/workspaceQC/nlppattern/resources/sampleMMO.txt")));
			// outputFile.delete();
			outputFile.createNewFile();
			os = new BufferedWriter(new OutputStreamWriter(
					new GZIPOutputStream(new FileOutputStream(outputFile, true))));
			utteranceScanner = new Scanner(is);
			utteranceScanner.useDelimiter("'EOU'\\.");

			String utterance;
			Matcher PMIDMatcher;
			Matcher relevantDataMatcher;
			Matcher evPatternMatcher;
			String pmid;
			
			String previousPMID = null;
			List<String> collectingCuis = new ArrayList<String>();
			
			while (utteranceScanner.hasNext()) {
				utterance = utteranceScanner.next();
				// System.out.println("Line " + ++i);
				// System.out.println("\t" + utterance);

				PMIDMatcher = pmidPattern.matcher(utterance);
				if (PMIDMatcher.find()) {
					pmid = PMIDMatcher.group(1);
					if(!pmid.equals(previousPMID)) {
						getCollector().put(collectingCuis, pmid);
						previousPMID = pmid;
						
						collectingCuis.clear();
					}
					if (pmidList.isRelevantPMID(Integer.parseInt(pmid))) {
						System.out.println(++count + "\tPMID: " + pmid);

						relevantDataMatcher = relevantData.matcher(utterance);
						while (relevantDataMatcher.find()) {
							os.append(relevantDataMatcher.group(0));
							if (relevantDataMatcher.group(0).startsWith(
									"mapping")) {
								evPatternMatcher = evPattern
										.matcher(relevantDataMatcher.group(0));
								while (evPatternMatcher.find()) {
									if (pmid.equals("164491")) {
										System.out.println(evPatternMatcher.group(0));
									}
									if (evPatternMatcher.groupCount() == 4) {
										if(evPatternMatcher.group(4).contains("dsyn")) {
											System.out.println("Name: "
													+ evPatternMatcher.group(3));
											System.out.println("CUID: "
													+ evPatternMatcher.group(2));
											System.out.println("SemanticTerm: "
													+ evPatternMatcher.group(4));
											if (!collectingCuis.contains(evPatternMatcher.group(2))) {
												collectingCuis.add(evPatternMatcher.group(2));
											}
										}
									}
								}
							}
						}

						os.append("'EOU'.\n");
					}
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
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			System.out.println("Done in "
					+ (System.currentTimeMillis() - startTime) + "ms");
			System.out.println("Itemset results (" + getCollector().getCuiItemsets().size() + "): " + getCollector().getCuiItemsets().toString());
		}
	}

	public CUICollector getCollector() {
		return collector;
	}

	public void setCollector(CUICollector collector) {
		this.collector = collector;
	}
}
