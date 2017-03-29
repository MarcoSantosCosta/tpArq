/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu;

import venus.cpu.memoria.MemoriaRegistrador;
import venus.cpu.unidades.IF;
import venus.cpu.unidades.unidadesComplementares.ExtensorSinal;
import venus.cpu.controller.ULA;

/**
 * Classe que resolve todos os jumps
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
     * Método que é executado todo clock para testar se há um desvio ou nao
     */
    public void clock(){
        checkJump();
    }
    /**
     * Método que verifica a flag na ula dependendo do cond da instrução de desvio condicional
     * @return Boolean representando o resultado da flag cond
     */
    private boolean getFlag(){
        short cond = If.getSubBin(4, 4);
        boolean aux = false;
        switch(cond){            
            case(0b0100):
                aux = ula.getNeg();
                break;
            case(0b0101):
                aux = ula.getZero();
                break;
            case(0b0110):
                aux = ula.getCarry();
                break;
            case(0b0111):
                aux = ula.getNegzero();
                break;
            case(0b0000):
                aux = ula.getTrue();
                break;
            case(0b0011):
                aux = ula.getOverflow();
                break;
            default:
                throw new RuntimeException("COND nao esta no formato especificado");
        }
        return aux;
    }
    /**
     * Método que verifica a existencia de um jump e desvia caso necessário
     */    
    private void checkJump(){
        if(unidadeDeControle.getJump()){
            ExtensorSinal extensor = ExtensorSinal.getInstance();
            if(unidadeDeControle.getOp() == 0b0){
                //desvio jf cond destino
                if(!getFlag()){
                    short desvio = extensor.exetender8();
                    If.setPcRelativo(desvio);
                }                
            }
            else if(unidadeDeControle.getOp() == 0b01){
                //desvio jt cond destino
                if(getFlag()){
                    short desvio = extensor.exetender8();
                    If.setPcRelativo(desvio);
                }                    
            }
            else if(unidadeDeControle.getOp() == 0b10){
                short desvio = extensor.exetender12();
                If.setPcRelativo(desvio);
            }
            else if(unidadeDeControle.getR() == 0b0){
                //jal B
                short reg = 7;
                bancoDeRegistradores.inserir(reg,If.getPc());
                //pega o valor do registrador B e seto ele como o desvio
                short desvio = bancoDeRegistradores.get(If.getSubBin(13,3));
                If.setPcPseudoDireto(desvio);                
            }
            else{
                //jr reg
                short desvio = bancoDeRegistradores.get(If.getSubBin(13, 3));
                If.setPcPseudoDireto(desvio);                
            }                            
        }        
    }
}
