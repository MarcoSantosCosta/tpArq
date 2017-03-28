/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.tradutor.controllers;

import org.junit.Test;

/**
 *
 * @author Marco
 */
public class TradutorTest {
    /**
     * Test of traduzir method, of class Tradutor.
     *
     * /**
     * Test of traduzir method, of class Tradutor.
     */
    @Test
    public void testTraduzir() {
        System.out.println("traduzir");
        String nomeOrigen = "multiplica.asm";
        String nomeDestino = "teste1.txt";
        Tradutor instance = Tradutor.getInstance();
        instance.traduzir(nomeOrigen, nomeDestino);
                
    }

}
