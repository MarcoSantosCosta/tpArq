/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu;

import venus.cpu.memoria.InstructionMemory;

/**
 *Classe que representa a unidade funcional ID contendo o pc e acessando a memoria de instruções do processador
 * @author Diego
 */
public class IF {
    private static IF instance = null;
    private short pc;
    private InstructionMemory memoriaDeInstrucoes;
    private String instrucaoAtual;
    /**
     * Método static que retorna a unidade funcional IF do processador
     * @return IF do processador
     */
    public static IF getInstance(){
       if(instance != null){
           return instance;
       }
       return new IF();
    }
    /**
    * Método construtor da classe IF que inicializa o PC com 0 e pega a memoria de instruções do processador
    */
    private IF(){
       pc = 0;
       memoriaDeInstrucoes = InstructionMemory.getInstance();
    }
    /**
     * Método que lê a instrução da memoria e atualiza o pc fazendo pc++;
     */
    public void clock(){        
        if((pc >= memoriaDeInstrucoes.getLimit()) || (pc < 0b0)){
            throw new ArrayIndexOutOfBoundsException("Erro: o PC acessou posicao invalida da memoria de instruções");
        }
        instrucaoAtual = memoriaDeInstrucoes.get(pc);
        pc++;
    }
    /**
     * Método que retorna parte da instrução em um short contendo o valor compreendido entre a posição inicial(inclusive)
     * e o tamanho passado
     * @param inicialPosition posicao inicial dos bits requeridos
     * @param size tamanho dos bits requeridos
     * @return short que representa os bits requeridos
     */
    public short getSubBin(int inicialPosition,int size){
        int endPosition = inicialPosition+size;
        String trecho = instrucaoAtual.substring(inicialPosition, endPosition);
        return Short.parseShort(trecho,2);
    }
    /**
     * Método que retorna o pc atual do IF
     * @return um short que representa o pc
     */
    public short getPc(){
        return pc;
    }
    /**
     * Método que altera o PC utilizando endereçamento relativo à PC
     * @param desvio posicao de desvio em relação ao pc atual (deve ser positivo para avançar na memória de instruções e negativo
     * para recuar na memória de instruções
     */
    public void setPcRelativo(short desvio){
        pc += desvio;
    }
    /**
     * Método que desvia para o endereço contido no registrador de jump
     * @param desvio 
     */
    public void setPcPseudoDireto(short desvio){
        pc = desvio;        
    }
}
