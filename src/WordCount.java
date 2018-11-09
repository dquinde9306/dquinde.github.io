package com.kennycason.kumo.examples;

import java.util.*;
import java.io.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




public class WordCount {
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {

		List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

		Collections.sort(list, new java.util.Comparator<Map.Entry<String, Integer> >() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// open the file
		Scanner console = new Scanner(System.in);
		String fileName = "text/twoheadedboy.txt";
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Scanner input = new Scanner(new File(classLoader.getResource(fileName).getFile()));

		// count occurrences
		HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
		while (input.hasNext()) {
			String next = input.next().toLowerCase();
			if (!wordCounts.containsKey(next)) {

				// TODO: remove symbols: ), ?, etc

				wordCounts.put(next, 1);
			} else {
				wordCounts.put(next, wordCounts.get(next) + 1);
			}
		}


		System.out.println("Total words = " + wordCounts.size());

		HashMap<String, Integer> sortedMapAsc = sortByValue(wordCounts);

		// Report frequencies
		try {
			PrintStream myconsel = new PrintStream(new File(classLoader.getResource("text/frequency_file.txt").getFile()));
			System.setOut(myconsel);
			for (String word : sortedMapAsc.keySet()) {
				int count = sortedMapAsc.get(word);
				System.out.println(count + ":\t" + word);
			}
		}
		catch(FileNotFoundException fx){
			System.out.println(fx);		}

	}

}
