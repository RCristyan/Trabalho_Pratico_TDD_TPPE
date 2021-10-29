package com.app;

public class Persistencia {
    private String inputFileName;
    private String outputFileName;
    private String outputPath;

    private Reader reader = null;
    private Writer writer = null;

    public Persistencia(String inputFileName, String outputPath, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.outputPath = outputPath;
        this.setUpReader();
        this.configureWriter();
    }

    public Persistencia() {
        // 
    }

    private void setUpReader() {
        if (this.reader == null) {
            try {
                this.reader = new Reader(inputFileName);
            } catch (Exception e) {
                System.out.println("Não foi possível instanciar o objeto de leitura!");
                System.out.println("Erro:" + e.getMessage());
            }
        }
    }

    public Reader getReader(){
        return reader;
    }

    public void setReader(Reader reader){
        this.reader = reader;
    }
    
    public String[] readFromFile() {
        String[] lines = null;
        
        setUpReader();
        lines = reader.read();
        
        return lines;
    }

    public Writer getWriter() {
        return writer;
    }

    public void configureWriter() {
        writer = new Writer();
        writer.setOutputPath(outputPath);
        writer.defineFormatoSaida();
    }

    public void writeToFile(String content) {
        writer.write(outputFileName, content);
    }   
}
