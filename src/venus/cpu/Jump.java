/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.cpu;

import venus.cpu.unidades.IF;

/**
 * Classe que resolve os jumps incon
 * @author Diego
 */
public class Jump {    
    private static Jump instance = null;
    private IF If;
    private Controle unidadeDeControle;
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
    }
    
    public void checkJump(){
        if(unidadeDeControle.getJump()){                
            ExtensorSinal extensor = ExtensorSinal.getInstance();
            if(unidadeDeControle.getOp() == 0b0){
                if(ula.){
                    //desvio relativo
                }
            }
            else if(unidadeDeControle.getOp() == 0b01){
                //desvio relativo
            }
            else if(unidadeDeControle.getOp() == 0b10){
               short desvio = If.getSubBin(4, 12);
            }
            else if(unidadeDeControle.getR() == 0b0){

            }
            else{

            }                            
        }        
    }
}
