package main.java.com.app;

import main.java.com.app.exceptions.DelimitadorInvalidoException;

public class Parser {
	// Responsável por realizar o parse das entradas
	
	private String delimiter = "-";
	
	public Parser() {}
	
	public Parser(String delimiter) throws DelimitadorInvalidoException {
		this.setDelimiter(delimiter);
	}
	
	public static void main(String[] args) {
		System.out.println("hello world");
	}

	public String getDelimiter() {
		return this.delimiter;
	}
	
	public void setDelimiter(String delimiter) throws DelimitadorInvalidoException {
		
		if(delimiter.length() > 1 && !delimiter.startsWith("\\")) {
			throw new DelimitadorInvalidoException("Delimitador inválido: apenas 1 caractere permitido.");
		}
		
		this.delimiter = delimiter;
	}

}
