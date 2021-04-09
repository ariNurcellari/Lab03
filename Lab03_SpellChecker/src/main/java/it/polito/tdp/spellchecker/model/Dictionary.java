package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	List<String> englishDictionary ;
	List<String> ItalianDictionary ;
	List<String> englishDictionaryList;
	
	public Dictionary() {
		this.englishDictionary = new ArrayList<String>() ;
		this.ItalianDictionary = new ArrayList<String>() ;
	}
	
	public void loadDictionary(String language) {
		if(language.equals("English")) {
		try {
			FileReader fr = new FileReader("src/main/resources/English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
					this.englishDictionary.add(word);
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file English.txt");
			}
		}
		else if(language.equals("Italian")){
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
						this.ItalianDictionary.add(word);
				}
				br.close();
				} catch (IOException e){
				System.out.println("Errore nella lettura del file Italian.txt");
				}
		}
	}
	
	public List<RichWord> spellCheckTextEnglish(List<String> inputTextList){
		
		List<RichWord> richWords = new ArrayList<RichWord>() ;
		RichWord richWord ;
		
		for(int i = 0 ; i < inputTextList.size() ; i++) {
			if(!englishDictionary.contains(inputTextList.get(i))) {
				richWord = new RichWord(inputTextList.get(i), false);
			richWords.add(richWord) ;
			}
			else {
				richWord = new RichWord(inputTextList.get(i), true);
			richWords.add(richWord);
			}
		}
		return richWords;
		}
	
	public List<RichWord> spellCheckTextItalian(List<String> inputTextList){
		
		List<RichWord> richWords = new ArrayList<RichWord>() ;
		RichWord richWord ;
		
		for(int i = 0 ; i < inputTextList.size() ; i++) {
			if(!ItalianDictionary.contains(inputTextList.get(i))) {
				richWord = new RichWord(inputTextList.get(i), false);
			richWords.add(richWord) ;
			}
			else {
				richWord = new RichWord(inputTextList.get(i), true);
			richWords.add(richWord);
			}
		}
		return richWords;
		}
}
