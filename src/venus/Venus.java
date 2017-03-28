/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus;

import venus.tradutor.controllers.Tradutor;


/**
 *
 * @author costa
 */
public class Venus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        String nomeOrigen = args[0];
        String nomeDestino = "teste1.txt";
        Tradutor instance = Tradutor.getInstance();
        instance.traduzir(nomeOrigen, nomeDestino);
    }

}
