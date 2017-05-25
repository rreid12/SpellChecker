package edu.wit.comp2000.group5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SpellChecker {
	
	private Dictionary dictionary;
	
	public SpellChecker(String fileLocation) {
		
		for(BagImplementationSelector implementation : BagImplementationSelector.values()) {
			
			Dictionary.setBagImplementation(implementation);
			
			System.out.println("Running with " + implementation + " Bag implementation:\n");
			
			dictionary = new Dictionary();
			
			ArrayList<String> listOfWords = new ArrayList<String>();
			try {
				Scanner inFile = new Scanner(new FileReader(fileLocation));
				while(inFile.hasNextLine()) {
					for(String word : inFile.nextLine().split(" ")) {
						word = word.toLowerCase();
						word = word.replaceAll("[^a-zA-Z\\s']", "");
						if(word != null || word != "") 
							listOfWords.add(word);
					}
					
				}
				
				listOfWords.removeAll(Arrays.asList("", null));
				inFile.close();
				
			} catch (FileNotFoundException e) {
				System.out.println("Dictionary file failed to load: "+ e.getMessage());
			}
			
			int correctCount = 0;
			int wrongCount = 0;
			
			if(!listOfWords.isEmpty() && listOfWords != null) {
				for(String word : listOfWords) {
					if (dictionary.spellCheck(word.toLowerCase())) {
						correctCount++;
					}
					else {
						System.out.println(word);
						wrongCount++;
					}
					
				}
			}
			
			System.out.println("CORRECT: " + correctCount);
			System.out.println("INCORRECT: " + wrongCount);
			System.out.println();
			
			/*if(!listOfWords.isEmpty())
				System.out.println(listOfWords.toString());*/
			
		}
		
	}
			
	public static void main(String[] args) {
		new SpellChecker("src/wit-attendance-policy.txt");
		
	}

}
