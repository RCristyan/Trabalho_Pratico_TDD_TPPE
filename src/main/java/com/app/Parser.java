package main.java.com.app;

import java.util.ArrayList;

import main.java.com.app.exceptions.DelimitadorInvalidoException;
import main.java.com.app.exceptions.InvalidDisplayOptionException;

public class Parser {
	// Responsável por realizar o parse das entradas
	
	private String delimiter = "-";
	private Reader reader;
	private String displayOption;
	private ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>> ();
	
	public Parser() {}
	
	public Parser(String delimiter) throws DelimitadorInvalidoException {
		this.setDelimiter(delimiter);
	}
	
	public static void main(String[] args) {
		System.out.println("hello world");
	}
	
	public void parse() {
		String[] lines = this.getReader().read();
		
		ArrayList<String> linha = new ArrayList<String>();
	    for(int i = 1; i <= lines.length; i++) {
	        
	    	if(i == lines.length) {
	    		this.matrix.add(linha);
	    		break;
	    	}
	    	
	        if(lines[i].startsWith("-")) {
	        	this.matrix.add(linha);
	        	i++;
	        	linha = new ArrayList<String>();
	        }
	        
	        linha.add(lines[i]);
	    }
	}

	public String getDelimiter() {
		return this.delimiter;
	}
	
	public ArrayList<ArrayList<String>> getEvolutions(){
		return this.matrix;
	}
	
	public ArrayList<String> getEvolution(int index){
		try {
			return this.matrix.get(index);			
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void setDelimiter(String delimiter) throws DelimitadorInvalidoException {
		
		if(delimiter.length() > 1 && !delimiter.startsWith("\\")) {
			throw new DelimitadorInvalidoException("Delimitador inválido: apenas 1 caractere permitido.");
		}
		
		this.delimiter = delimiter;
	}

	public Reader getReader() {
		return this.reader;
	}
	
	public void setReader(Reader r) {
		this.reader = r;
	}
	
	public String getDisplayOption() {
		return this.displayOption;
	}
	
	public void setDisplayOption(String option) throws InvalidDisplayOptionException {
		ArrayList<String> invalidOptions = new ArrayList<String>();
		invalidOptions.add("linhas");
		invalidOptions.add("colunas");
		
		if(!invalidOptions.contains(option)) {
			throw new InvalidDisplayOptionException("Opção inválida: Escolha linhas ou colunas");
		}
		
		this.displayOption = option;
	}
	
	public String getFormatedEvolution(int index) {
		String formatedLine = "" + index;
		ArrayList<String> lineItems = this.getEvolution(index);
		
		if(lineItems == null) {
			return null;
		}
		
		for(String item : lineItems) {
			formatedLine += ";" + item;
		}
		
		return formatedLine;
	}
	
	public ArrayList<String> getFormatedText() {
		ArrayList<String> formatedText = new ArrayList<String>();
		
		for(int i = 0;; i++) {
			String evo = this.getFormatedEvolution(i);
			if(evo == null) {
				break;
			}
			
			formatedText.add(evo);
		}
		
		if(this.displayOption.equals("linhas")) {
			return formatedText;			
		}
		
		return this.getFormatedTextInColumns(formatedText);
	}
	
	private ArrayList<String> getFormatedTextInColumns(ArrayList<String> formatedText){
		ArrayList<String> formatedTextInColumns = new ArrayList<String>();
		String line = new String();
		
		for(int j = 0; j < 12;j++) {
			line = new String();
			for(int i = 0; i < formatedText.size(); i++) {
					line += formatedText.get(i).split(this.delimiter)[j] + this.delimiter;
			}
			formatedTextInColumns.add(line);
		}
		
		formatedTextInColumns.add(line);
		return formatedTextInColumns;
	}

}
