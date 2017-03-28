/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu;

import venus.cpu.memoria.MemoriaPrincipal;
import cpu.memoria.MemoriaRegistrador;
import java.io.BufferedReader;
import java.io.FileReader;
import venus.cpu.memoria.InstructionMemory;

/**
 * Classe que representa o processador Venus. Todas as operações são realizadas aqui e todas as unidades funcionais encontram-se
 * aqui
 * @author Diego
 */
public class Cpu {
    private String nomePrograma;
    private InstructionMemory memoriaDeInstrucoes;
    private MemoriaPrincipal memoriaPrincipal;
    private MemoriaRegistrador bancoDeRegistradores;
    private IF If;
    
    /**
     * Construtor do Processador que
     * @param nomePrograma 
     */
    public Cpu(String nomePrograma){
        this.nomePrograma = nomePrograma;
        memoriaDeInstrucoes = InstructionMemory.getInstance();
        memoriaPrincipal = MemoriaPrincipal.getInstance();
        bancoDeRegistradores = MemoriaRegistrador.getInstance();
        If = IF.getInstance();
        carregarPrograma();
    }
    /**
     * Método que carrega o programa do usuario na memória de instruções
     */
    private void carregarPrograma(){
        try{
            BufferedReader arquivo = new BufferedReader(new FileReader(nomePrograma));
            String linha = arquivo.readLine();
            while(linha != null){                
                memoriaDeInstrucoes.add(linha);
                linha = arquivo.readLine();
            }
            arquivo.close();
        }
        catch(Exception e){
            //Erro
        }
    }
}
