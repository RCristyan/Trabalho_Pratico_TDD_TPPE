package main.java.com.app;

public class Parser {
	// Responsável por realizar o parse das entradas
	private String delimiter = "-";
	
	public Parser() {}
	
	public Parser(String delimiter) {
		this.delimiter = delimiter;
	}
	
	public static void main(String[] args) {
		System.out.println("hello world");
	}

	public String getDelimiter() {
		return this.delimiter;
	}

}
