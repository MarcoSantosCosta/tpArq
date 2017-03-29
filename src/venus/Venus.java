/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus;

import venus.cpu.Cpu;
import venus.tradutor.controllers.Tradutor;

/**
 *
 * @author costa
 */
public class Venus {

    public static void testar(Cpu cpu) {
        for (int i = 0; i < 100; i++) {
            System.err.println(i);
            cpu.clock();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nomeOrigen = args[0];
        String nomeDestino = args[0] + "Out.txt";
        Tradutor instance = Tradutor.getInstance();
        instance.traduzir(nomeOrigen, nomeDestino);
        Cpu cpu = new Cpu(nomeDestino);
        testar(cpu);        
    }

}
