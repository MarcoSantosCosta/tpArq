package cpu.memoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Ordem dos registradores:
 * 0-r0
 * 1-r1
 * 2-r2
 * 3-r3
 * 4-r4
 * 5-r5
 * 6-r6
 * 7-r7
 * 8-PC
 * 9-ra
 * @author neche
 */
public class MemoriaRegistrador {
    private List<Short> mem;
    private int limite;
    private static MemoriaRegistrador instance = null;
    
    private MemoriaRegistrador(){
        mem = new ArrayList<Short>();
        limite = 9;
        for(int i = 0; i <= limite;i++){
            mem.add((short)0);
        }
    };
    
    public static MemoriaRegistrador getInstance(){
        if(instance == null)
            return (new MemoriaRegistrador());
        return instance;
    }
    
    /**
     * Insere no registrador o valor passado em valor
     * @param registrador numero do registrador[0...7]
     * @param valor valor a ser colocado no registrador
     */
    public void inserir(int registrador,short valor){
        if(registrador < 0 || registrador > limite){
            throw new RuntimeException("Registrador " + registrador + " e invalido.");
        }
        if(valor < -32768 || valor > 32767){
            throw new RuntimeException("Valor a ser inserido no registrador " + registrador + " e invalido.");
        }
        
        mem.add(registrador, valor);
    }
    
    /**
     * Retorna o valor contido no registrador
     * @param registrador numero do registrador[0...7]
     * @return valor do registrador
     */
    public short get(int registrador){
        if(registrador < 0 || registrador > limite){
            throw new RuntimeException("Registrador " + registrador + " e invalido.");
        }
        return mem.get(registrador);
    }
    
    public void printaMemoria(){
        for(Short i : mem){
            System.out.println("valor "+i+"\n");
        }
    }
}
