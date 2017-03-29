/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu;

import venus.cpu.memoria.MemoriaRegistrador;
import venus.cpu.unidades.IF;
import venus.cpu.unidades.unidadesComplementares.ExtensorSinal;

/**
 * Classe que resolve os jumps incon
 * @author Diego
 */
public class Jump {    
    private static Jump instance = null;
    private IF If;
    private Controle unidadeDeControle;
    private MemoriaRegistrador bancoDeRegistradores;
    private ULA ula;
    
    public static Jump getInstance(){
        if(instance != null){
            return instance;
        }
        instance = new Jump();
        return instance;
    }
    
    private Jump(){
        If = IF.getInstance();
        ula = ULA.getInstance();
        unidadeDeControle = Controle.getInstance();
        bancoDeRegistradores = MemoriaRegistrador.getInstance();
    }
    /**
     * Método que é executado toda vez que é
     */
    public void clock(){
        checkJump();
    }
    /**
     * Método que verifica a existencia de um jump e desvia caso necessário
     */    
    private void checkJump(){
        if(unidadeDeControle.getJump()){
            ExtensorSinal extensor = ExtensorSinal.getInstance();
            if(unidadeDeControle.getOp() == 0b0){
                if(ula.){
                    //desvio relativo
                }
            }
            else if(unidadeDeControle.getOp() == 0b01){
                if(ula.){
                    //desvio relativo    
                }                    
            }
            else if(unidadeDeControle.getOp() == 0b10){
                short desvio = extensor.exetender12();
                If.setPcRelativo(desvio);
            }
            else if(unidadeDeControle.getR() == 0b0){
                short reg = 7;
                bancoDeRegistradores.inserir(reg,If.getPc());
                //pega o valor do registrador B e seto ele como o desvio
                short desvio = bancoDeRegistradores.get(If.getSubBin(13,3));
                If.setPcPseudoDireto(desvio);                
            }
            else{
                short desvio = bancoDeRegistradores.get(If.getSubBin(13, 3));
                If.setPcPseudoDireto(desvio);                
            }                            
        }        
    }
}
