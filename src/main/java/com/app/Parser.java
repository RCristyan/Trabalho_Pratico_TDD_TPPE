package main.java.com.app;

import java.util.ArrayList;
import main.java.com.app.exceptions.DelimitadorInvalidoException;

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
		return this.matrix.get(index);
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
	
	public void setDisplayOption(String option) {
		this.displayOption = option;
	}

}
