package com.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Writer {
	// Responsável pela escrita dos arquivos de saída do programa
    private String outputPath;
    private String formatoSaida;


    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getFormatoSaida() {
        return formatoSaida;
    }

    public void setFormatoSaida(String formatoSaida) {
        this.formatoSaida = formatoSaida;
    }

    public boolean pathAllowWrite(String path) throws EscritaNaoPermitidaException{
        File f = new File(path);

        if(f.canWrite()){
            return true;
        }else{
            throw new EscritaNaoPermitidaException("Não há permição de escrita no caminho de destino!");
        }
    }

    private FileWriter openFile(String fileName) throws IOException{
        File file = new File(outputPath + '/' + fileName);
        file.createNewFile();
        return new FileWriter(file.getAbsoluteFile());
    }

    public void write (String fileName, String content) {
        try {
            FileWriter fw = openFile(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public boolean defineFormatoSaida(){
        return defineFormatoSaida(new Scanner(System.in));
    }

    public boolean defineFormatoSaida(Scanner scanner){
        System.out.println("Defina o formato de saida do resultado");
        System.out.println("1 - linha");
        System.out.println("2 - coluna");

        switch (scanner.nextInt()) {
            case 1:
                setFormatoSaida("linha");
                break;
            case 2:
                setFormatoSaida("coluna");
                break;
            default:
                System.out.println("Digite um valor válido!");
                return false;
        }

        return true;
    }
}
