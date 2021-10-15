package com.app;

import java.io.File;
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
        return "linha"; // falsificação
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

    public void defineFormatoSaida(Scanner scanner){
    }
}
