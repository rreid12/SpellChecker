package edu.wit.comp2000.group5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Names: Ryan Reid and Andres Prato
 * Group#: 5
 * Course: COMP 2000-01
 * Assignment: Bag ADT Application (SpellChecker)
 */
/**
 * 
 * @author pratoa & reidr (Andres Prato & Ryan Reid)
 * Uses a Dictionary object to spell check a file
 */
public class SpellChecker {
	
	private Dictionary dictionary;
	
	/**
	 * 
	 * @param fileLocation
	 * Constructor
	 */
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
					}//end for
					
				}//end while
				
				listOfWords.removeAll(Arrays.asList("", null));
				inFile.close();
				
			} catch (FileNotFoundException e) {
				System.out.println("Dictionary file failed to load: "+ e.getMessage());
			}//end try/catch
			
			int correctCount = 0;
			int wrongCount = 0;
			ArrayList<String> wrongWords = new ArrayList<>();
			
			if(!listOfWords.isEmpty() && listOfWords != null) {
				for(String word : listOfWords) {
					if (dictionary.spellCheck(word.toLowerCase())) {
						correctCount++;
					}//end if
					else {
						wrongWords.add(word);
						wrongCount++;
					}//end else
					
				}//end for
			}//end if
			
			System.out.println("Correct: " + correctCount);
			System.out.println("Incorrect: " + wrongCount);
			System.out.println("Words mispelled: " );
			for (int i = 0; i < wrongWords.size(); i++){
				System.out.println(wrongWords.get(i));
			}//end for
			System.out.println();
			
		}
		
	}//end constructor
			
	public static void main(String[] args) {
		//runs the application
		
		System.out.println("CHECKING FILE: wit-attendance-policy.txt");
		new SpellChecker("wit-attendance-policy.txt");

		System.out.println("CHECKING FILE: the-lancashire-cotton-famine.txt");
		new SpellChecker("the-lancashire-cotton-famine.txt");

		System.out.println("CHECKING FILE: sources.txt");
		new SpellChecker("sources.txt");
	}//end main

}//end SpellChecker
