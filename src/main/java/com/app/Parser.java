package com.app;

import java.nio.file.Path;
import java.util.ArrayList;

import com.app.exceptions.DelimitadorInvalidoException;
import com.app.exceptions.InvalidDisplayOptionException;

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
		String memoryCurrentPath = "analysisMemory.out";
		String timeCurrentPath = "analysisTime.out";

		String memoryOutputPath = Path.of("").toAbsolutePath().toString();
		String timeOutputPath = Path.of("").toAbsolutePath().toString();

		ArrayList<String> memoryContent = null;
		ArrayList<String> timeContent = null;

		Reader reader = null;
		Parser parser = null;
		Writer writer = null;

		try {
			reader = new Reader(memoryCurrentPath);
		}catch (Exception ex){
			System.out.println("Não foi possível instanciar o objeto de leitura!");
			System.out.println("Erro:" + ex.getMessage());
		}

		try {
			writer = new Writer();

			parser = new Parser(";");

			writer.defineFormatoSaida();

			String formato = writer.getFormatoSaida();

			if (formato == "linha") {
				parser.setDisplayOption("linhas");
			} else {
				parser.setDisplayOption("colunas");
			}

			parser.setReader(reader);

			parser.parse();

			memoryContent = parser.getFormatedText();
		}catch (Exception ex){
			System.out.println("Erro:" + ex.getMessage());
		}

		try {
			writer.pathAllowWrite(memoryOutputPath);

			String outputContent = "";

			for (int i = 0; i < memoryContent.size(); i++){
				outputContent += memoryContent.get(i) + "\n";
			}

			writer.write("memory.txt" ,outputContent);
		}catch (Exception ex){
			System.out.println("Não foi possível Realizar a escrita do arquivo!");
			System.out.println("Erro:" + ex.getMessage());
		}
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

		System.out.println(option);
		
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
