/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu;

import venus.cpu.unidades.unidadesComplementares.Jump;
import venus.cpu.unidades.Controle;
import venus.cpu.memoria.MemoriaPrincipal;
import venus.cpu.memoria.MemoriaRegistrador;
import java.io.BufferedReader;
import java.io.FileReader;
import venus.cpu.memoria.InstructionMemory;
import venus.cpu.unidades.IF;
import venus.cpu.unidades.ID;
import venus.cpu.unidades.ULA;
import venus.cpu.unidades.MEM;
import venus.cpu.unidades.WB;

/**
 * Classe que representa o processador Venus. Todas as operações são realizadas
 * aqui e todas as unidades funcionais encontram-se aqui
 *
 * @author Diego
 */
public class Cpu {

    private String nomePrograma;
    private InstructionMemory memoriaDeInstrucoes;
    private MemoriaPrincipal memoriaPrincipal;
    private MemoriaRegistrador bancoDeRegistradores;
    private IF If;
    private Controle controle;
    private Jump jump;
    private ULA ula;
    private MEM mem;
    private WB wb;
    private ID id;

    /**
     * Construtor do Processador que
     *
     * @param nomePrograma
     */
    public Cpu(String nomePrograma) {
        this.nomePrograma = nomePrograma;
        memoriaDeInstrucoes = InstructionMemory.getInstance();
        memoriaPrincipal = MemoriaPrincipal.getInstance();
        bancoDeRegistradores = MemoriaRegistrador.getInstance();
        If = IF.getInstance();
        id = ID.getInstance();
        controle = Controle.getInstance();
        jump = Jump.getInstance();
        ula = ULA.getInstance();
        mem = MEM.getInstance();
        wb = WB.getInstance();
        carregarPrograma();
    }

    /**
     * Método que carrega o programa do usuario na memória de instruções
     */
    private void carregarPrograma() {
        try {
            BufferedReader arquivo = new BufferedReader(new FileReader(nomePrograma));
            String linha = arquivo.readLine();
            while (linha != null) {
                System.err.println(linha);
                memoriaDeInstrucoes.add(linha);
                linha = arquivo.readLine();
            }
            arquivo.close();
        } catch (Exception e) {
            //Erro
        }
    }

    public void clock() {
        If.clock();
        controle.clock();
        jump.clock();
        id.clock();
        ula.clock();
        mem.clock();
        wb.clock();
        System.out.println("pos1000:"+MemoriaPrincipal.getInstance().get(1000));
    }

}
