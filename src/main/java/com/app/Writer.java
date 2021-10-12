package main.java.com.app;

import java.io.File;

public class Writer {
	// Responsável pela escrita dos arquivos de saída do programa
    private String outputPath;

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public boolean pathAllowWrite(String path) throws EscritaNaoPermitidaException{
        File f = new File(path);

        if(f.canWrite()){
            return true;
        }else{
            throw new EscritaNaoPermitidaException("Não há permição de escrita no caminho de destino!");
        }
    }
}
