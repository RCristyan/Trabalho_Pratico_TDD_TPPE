package com.app;

import java.util.ArrayList;

public class Runner {
    private ArrayList<String> ResultContent = null;
    private Reader reader = null;
    private Writer writer = null;

    private String currentPath;
    private String outputPath;
    private String outputFileName;
    private Parser parser;

    public Runner(String currentPath, String OutputPath, String outputFileName, Parser parser){
        this.currentPath = currentPath;
        this.outputPath = OutputPath;
        this.outputFileName = outputFileName;
        this.parser = parser;
    }

    private void initReader() throws ArquivoNaoEncontradoException{
        reader = new Reader(currentPath);
    }

    private void initWriter() {
        writer = new Writer();
        writer.setOutputPath(outputPath);
        writer.defineFormatoSaida();
    }

    private void parseFile() {
        try {

            parser.setDelimiter(";");
            
			String formato = writer.getFormatoSaida();

			if (formato == "linha") {
				parser.setDisplayOption("linhas");
			} else {
				parser.setDisplayOption("colunas");
			}
            
			parser.setReader(reader);
            
			parser.parse();
        }
        catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    private void writeToFile() {
        try {
			if(writer.pathAllowWrite(outputPath))
				writer.setOutputPath(outputPath);

			String outputContent = "";

			for (int i = 0; i < ResultContent.size(); i++){
				outputContent += ResultContent.get(i) + "\n";
			}

			writer.write(outputFileName ,outputContent);
		}catch (Exception ex){
			System.out.println("Não foi possível Realizar a escrita do arquivo!");
			System.out.println("Erro:" + ex.getMessage());
		}
    }

    public void execute(){
		try {
			initReader();
		}catch (Exception ex){
			System.out.println("Não foi possível instanciar o objeto de leitura!");
			System.out.println("Erro:" + ex.getMessage());
		}

		try {
			initWriter();
            
            parseFile();

			ResultContent = parser.getFormatedText();
		}catch (Exception ex){
			System.out.println("Erro:" + ex.getMessage());
		}

        writeToFile();
    }
}
