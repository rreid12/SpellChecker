package edu.wit.comp2000.group5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
	
	//private static BagImplementationSelector bagImplementationSelection = BagImplementationSelector.LINKED;
	private BagInterface<String> theBag = null;
	private static BagImplementationSelector bagImplementationSelection;
	
/*	public Dictionary() {
		theBag = chooseDictionaryImplementation(BagImplementationSelector.LINKED);
		
		addWordsToDictionary(getListOfWordsFromFileInput());
	}*/
	
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
		
		}
		
		addWordsToDictionary(getListOfWordsFromFileInput());
		
		
/*		theBag = null;
		ArrayList<String> words = getListOfWordsFromFileInput();
		theBag = chooseDictionaryImplementation(bagImplementationSelection);
		addWordsToDictionary(words);*/
		} //end constructor
	
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
		}
	}
	
	private BagInterface<String> chooseDictionaryImplementation(BagImplementationSelector bagImplementationSelection) {
		
		return null;
	}
	
	private void addWordsToDictionary(ArrayList<String> words) {
		
		for(String word : words) {
			theBag.add(word);
		}
	}
	
	public boolean spellCheck(String word) {
		return theBag.contains(word.toLowerCase());
	}
	
	public int getWordCount() {
		return theBag.getCurrentSize();
	}
	
	@Override
	public String toString() {
		//TODO implement this method in each of the 3 implementations (for use here)
		return null;
	}
	
	public static void setBagImplementation(BagImplementationSelector implementationSelector) {
		bagImplementationSelection = implementationSelector;
	}
	
	public static BagImplementationSelector getBagImplementationSelection() {
		return bagImplementationSelection;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dictionary d = new Dictionary();
		
		System.out.println(d.toString());
		System.out.println("SIZE: " + d.getWordCount());
		System.out.println("Taco in the list? " + d.spellCheck("Taco"));
		
	}

} //end Dictionary



