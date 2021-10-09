package main.java.com.app;

public class Parser {
	// Responsável por realizar o parse das entradas
	private char delimiter = '-';
	
	public Parser() {}
	
	public Parser(char delimiter) {
		this.delimiter = delimiter;
	}
	
	public static void main(String[] args) {
		System.out.println("hello world");
	}

	public char getDelimiter() {
		return this.delimiter;
	}

}
