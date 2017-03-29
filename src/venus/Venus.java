/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus;

import venus.cpu.Cpu;
import venus.cpu.memoria.MemoriaPrincipal;
import venus.cpu.memoria.MemoriaRegistrador;
import venus.tradutor.controllers.Tradutor;

/**
 *
 * @author costa
 */
public class Venus {
    public static void testar(Cpu cpu) {
        MemoriaRegistrador mem = MemoriaRegistrador.getInstance();
        int i = 0;
        while (MemoriaRegistrador.TESTE) {
            System.out.println("CLOCK: "+i);
            for (int j = 0; j < 8; j++) {
                System.out.println("R" + j + ": " + mem.get((short) j));
            }
            cpu.clock();        
            i++;
        }
        MemoriaPrincipal memo = MemoriaPrincipal.getInstance();
        System.out.println("TESTE: " +memo.get(5));

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
