package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dictionary ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextArea txtInsert;

    @FXML
    private Button SpellCeck;

    @FXML
    private TextArea txtWrongText;

    @FXML
    private Label txtNumberErrors;

    @FXML
    private Button ClearText;

    @FXML
    private Label prestazioni;

    @FXML
    void doClearText(ActionEvent event) {
    	txtWrongText.clear();
    	txtInsert.clear();
    	txtNumberErrors.setText("");;
    	prestazioni.setText("");
    }

    @FXML
    void doSpellChek(ActionEvent event) {
    	
    	String testo = txtInsert.getText();
    	String lingua = comboBox.getValue() ;
    	
    	txtWrongText.setText(lingua);
    	// carico il file in memoria
    	dictionary.loadDictionary(lingua);
    	
    	testo = testo.replaceAll("[.,?\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "") ;
    	if(testo.length() == 0) {
    		txtWrongText.setText("INSERIRE UNA FRASE IN ITALIANO/INGLESE!!");
    		return ;
    	}
    	
    	List<String> myList = new ArrayList<String>(Arrays.asList(testo.split(" ")));
    	double tempo1 = 0.0;
    	double tempo2 = 0.0;
    
    	List<RichWord> listTempList = null;
    	if(lingua.equals("English")) {
    		tempo1 = System.nanoTime();
    		listTempList = new ArrayList<RichWord>(dictionary.spellCheckTextEnglish(myList));
    		 tempo2 = System.nanoTime();
    	}
    	
    	else if(lingua.equals("Italian")) {
    		tempo1 = System.nanoTime();
        	listTempList = new ArrayList<RichWord>(dictionary.spellCheckTextItalian(myList));
        	tempo2 = System.nanoTime();
        	
    	}
    	String resultAssenti = "";
    	int cnt = 0;
    
		for(RichWord richWord : listTempList) {
    		if(!richWord.isCorretta()) {
    			resultAssenti += richWord.getWord()+"\n";
    			cnt ++ ;
    		}
    	}
    	
    	txtWrongText.setText(resultAssenti);
    	prestazioni.setText("Spell Check completed in "+((tempo2-tempo1)/1e9)+" seconds");
    	txtNumberErrors.setText("The text contains "+cnt+" errors");
    
    }
    
    public void setModel(Dictionary dictionary) {
    	this.dictionary = dictionary ;
    	comboBox.getItems().addAll("English","Italian") ;
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
        assert SpellCeck != null : "fx:id=\"SpellCeck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrongText != null : "fx:id=\"txtWrongText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumberErrors != null : "fx:id=\"txtNumberErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert ClearText != null : "fx:id=\"ClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert prestazioni != null : "fx:id=\"prestazioni\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}