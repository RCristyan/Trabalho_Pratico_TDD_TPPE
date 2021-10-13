package main.java.com.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Reader {
	// Responsável pela leitura dos arquivos de entrada do programa
	private String fileName;
	private FileInputStream fileInputStream = null;

	private void openFile() throws ArquivoNaoEncontradoException {	
		try {
			this.fileInputStream = new FileInputStream(fileName);
		} catch (Exception e) {
			throw new ArquivoNaoEncontradoException(fileName);
		}
	} 

	public Reader(String fileName) throws ArquivoNaoEncontradoException {
		this.fileName = fileName;
		this.openFile();
	}
	
	public String[] read() {
		BufferedReader buffer = new BufferedReader(new java.io.InputStreamReader(fileInputStream));
		String line = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			while ((line = buffer.readLine()) != null) {
				lines.add(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lines.toArray(new String[lines.size()]);
	}
}
