package venus.persist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author neche
 */
public class Arquivo {
    private static Arquivo instance = null;
    
    private Arquivo(){};
    
    public static Arquivo getInstance(){
        if(instance == null)
            return (new Arquivo());
        return instance;
    }
    
    /**
     * Retorna uma String com tudo do arquivo
     * @param nomeArquivo Nome do arquivo
     * @return String com tudo lido ou null caso ocorra algum problema
     */
    public String ler(String nomeArquivo){
        try {
            BufferedReader arq = new BufferedReader(new FileReader(nomeArquivo));
            String linha = arq.readLine();
            String retorno = "";
            while (linha != null) {
                retorno += linha + "\n";
                linha = arq.readLine();
            }
            return retorno;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Recebe um vetor com as instrucoes em binario e escreve no arquivo
     * @param binInstrucoes Vetor com as instrucoes
     * @param nomeArquivo Nome do arquivo
     */
    public void escrever(String[] binInstrucoes,String nomeArquivo){
        try {
            FileWriter arq = new FileWriter(nomeArquivo);
            for (String i : binInstrucoes) {
                arq.write(i);
                arq.write("\n");
            }
            arq.close();
        }
        catch (Exception e) {}
    }
}
