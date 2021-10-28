package com.app;

import java.util.ArrayList;

public class Runner {
    private ArrayList<String> ResultContent = null;

    private String currentPath;
    private String outputPath;
    private String outputFileName;
    private Parser parser;
    private Persistencia persistencia;

    public Runner(String currentPath, String outputPath, String outputFileName, Parser parser){
        this.currentPath = currentPath;
        this.outputPath = outputPath;
        this.outputFileName = outputFileName;
        this.parser = parser;
        this.persistencia = new Persistencia(currentPath, outputPath, outputFileName);
    }

    private void parseFile() {
        try {

            parser.setDelimiter(";");
            
			String formato = persistencia.getWriter().getFormatoSaida();

			if (formato == "linha") {
				parser.setDisplayOption("linhas");
			} else {
				parser.setDisplayOption("colunas");
			}
            
			parser.setReader(persistencia.getReader());
            
			parser.parse();
        }
        catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    private void writeToFile() {
        try {
			if(persistencia.getWriter().pathAllowWrite(outputPath))
				persistencia.getWriter().setOutputPath(outputPath);

			String outputContent = "";

			for (int i = 0; i < ResultContent.size(); i++){
				outputContent += ResultContent.get(i) + "\n";
			}

			persistencia.writeToFile(outputContent);
		}catch (Exception ex){
			System.out.println("Não foi possível Realizar a escrita do arquivo!");
			System.out.println("Erro:" + ex.getMessage());
		}
    }

    public void execute(){
		try {

            parser.setPersistencia(persistencia);
            
            parseFile();

			ResultContent = parser.getFormatedText();
		}catch (Exception ex){
			System.out.println("Erro:" + ex.getMessage());
		}

        writeToFile();
    }
}
