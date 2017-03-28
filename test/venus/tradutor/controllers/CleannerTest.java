/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venus.tradutor.controllers;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author costa
 */
public class CleannerTest {
    
    @Test
    public void testVacuos() {
        Cleanner instance = Cleanner.getInstance();
        String test = "\n\n\nAAAAAA\n\n\n\n\nBBBB\t\t\tBB  \n B";
        test = instance.clean(test);
        assertFalse(test.contains("\n\n"));     
    }
    @Test
    public void testEspacos() {
        Cleanner instance = Cleanner.getInstance();
        String test = "\n\n\nAAAAAA\n\n\n\n\nBBBB\t\t\tBB  \n B";
        test = instance.clean(test);
        assertFalse(test.contains("  "));     
    }
    @Test
    public void testComentarios() {
        Cleanner instance = Cleanner.getInstance();
        String test = "\n\n\nAAAAAA\n\n\n\n\nBBBB\t\t\tBB  \n B";
        test = instance.clean(test);
        assertFalse(test.contains(";"));     
    }

}
