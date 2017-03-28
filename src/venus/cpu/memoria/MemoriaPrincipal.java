package cpu.memoria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author neche
 */
public class MemoriaPrincipal {
    private List<Short> mem;
    private int limite;
    private static MemoriaPrincipal instance = null;
    
    private MemoriaPrincipal(){
        mem = new ArrayList<Short>();
        limite = 63999;
        for(int i = 0; i <= limite;i++){
            mem.add((short)0);
        }
    };
    
    public static MemoriaPrincipal getInstance(){
        if(instance == null)
            return (new MemoriaPrincipal());
        return instance;
    }
    
    /**
     * Insere na <pos> o <valor> passado
     * @param pos posicao da memoria[0...63999]
     * @param valor valor a ser colocado na memoria
     */
    public void inserir(int pos,short valor){
        if(pos < 0 || pos > limite){
            throw new RuntimeException("Posicao " + pos + " e invalido.");
        }
        if(valor < -32768 || valor > 32767){
            throw new RuntimeException("Valor a ser inserido na posicao " + pos + " e invalido.");
        }
        
        mem.add(pos, valor);
    }
    
    /**
     * Retorna o valor contido na <pos>
     * @param pos posicao da memoria[0...63999]
     * @return valor do que esta na memoria
     */
    public short get(int pos){
        if(pos < 0 || pos > limite){
            throw new RuntimeException("Posicao " + pos + " e invalido.");
        }
        return mem.get(pos);
    }
    
    public void printaMemoria(){
        int cont = 0;
        for(Short i : mem){
            System.out.println(cont + "- valor "+i+"\n");
            cont++;
        }
    }
}
