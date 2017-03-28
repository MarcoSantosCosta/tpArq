package venus.tradutor.instrucoes;

/**
 *
 * @author neche
 */
public class Halt implements Instrucao{
    private String bin;
    public Halt(){
        bin = "1111111111111111";
    }
    public String getBin(){
        return bin;
    }
}