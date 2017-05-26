package edu.wit.comp2000.group5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Names: Ryan Reid and Andres Prato
 * Group#: 5
 * Course: COMP 2000-01
 * Assignment: Bag ADT Application (SpellChecker)
 */
/**
 * @author reidr & pratoa (Ryan Reid & Andres Prato)
 * Creates a dictionary using the Bag ADT
 *
 */
public class Dictionary {
	
	private BagInterface<String> theBag = null;
	private static BagImplementationSelector bagImplementationSelection;
	
	/**
	 * Constructor
	 */
	public Dictionary() {
		
		switch(bagImplementationSelection) {
			case LINKED:
				theBag = new LinkedBag<String>();
				
			case FIXED_ARRAY:
				theBag = new ArrayBag<String>();
				
			case RESIZABLE_ARRAY:
				theBag = new ResizableArrayBag<String>();
				
			default:
				theBag = new LinkedBag<String>();
		
		} //end switch
		
		addWordsToDictionary(getListOfWordsFromFileInput());
		
		} //end constructor
	
	/**
	 * 
	 * @return 
	 * 
	 * Takes input from a text file and returns an array list of words
	 */
	private ArrayList<String> getListOfWordsFromFileInput() {
		
		ArrayList<String> listOfWords = new ArrayList<String>();
		try {
			Scanner inFile = new Scanner(new FileReader("american-english-JL.txt"));
			while(inFile.hasNextLine()) {
				listOfWords.add(inFile.nextLine().toLowerCase());
			}
			inFile.close();
			
			return listOfWords;

		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file failed to load: "+ e.getMessage());
			return null;
		}//end try/catch
		
	}//end method getListOfWordsFromFileInput()
	
	/**
	 * @param words
	 * 
	 * Takes the list of words and adds them to theBag
	 */
	private void addWordsToDictionary(ArrayList<String> words) {
		
		for(String word : words) {
			theBag.add(word);
		}
	}//end addWordsToDictionary
	
	/**
	 * @param word
	 * @return
	 * Checks to see if the word is in the dictionary
	 */
	public boolean spellCheck(String word) {
		return theBag.contains(word.toLowerCase());
	}//end method spellCheck()
	
	/**
	 * @return
	 * Gets the size of the dictionary
	 */
	public int getWordCount() {
		return theBag.getCurrentSize();
	}//end method getWordCount()
	
	/**
	 * @param implementationSelector
	 * sets the BagImplementation for the dictionary(Linked, fixed, resizable)
	 */
	public static void setBagImplementation(BagImplementationSelector implementationSelector) {
		bagImplementationSelection = implementationSelector;
	}//end method setBagImplementation()
	
	/**
	 * @return
	 * gets the BagImplementation for the dictionary
	 */
	public static BagImplementationSelector getBagImplementationSelection() {
		return bagImplementationSelection;
	}//end method getBagImplementationSelection()
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testing
		
		for(BagImplementationSelector implementation : BagImplementationSelector.values()) {
			
			Dictionary.setBagImplementation(implementation);
			Dictionary d = new Dictionary();
			System.out.println("SIZE: " + d.getWordCount());
			System.out.println("Taco in the list? " + d.spellCheck("Taco"));
		}
		
		
	}// end method main

} //end Dictionary



