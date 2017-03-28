/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego
 */
public class DicionarioTest {
    
    public DicionarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class Dicionario.
     */
    @Test
    public void testGetInstance_3args() {
        Dicionario dicionario = Dicionario.getInstance("op","func", "func", "reg", "r", "cond");
        assertNotNull(dicionario);
    }

    /**
     * Test of getFunc method, of class Dicionario.
     */
    @Test
    public void testGetFunc() {
        Dicionario dicionario = Dicionario.getInstance("op","func", "func", "reg", "r", "cond");       
        short result = dicionario.getFunc("add");
        assertEquals(0b1,result);
    }

    /**
     * Test of getOp method, of class Dicionario.
     */
    @Test
    public void testGetOp() {
        Dicionario dicionario = Dicionario.getInstance("op","func", "func", "reg", "r", "cond");      
        short result = dicionario.getOp("add");
        assertEquals(0b1,result);
    }

    /**
     * Test of getType method, of class Dicionario.
     */
    @Test
    public void testGetType() {
        Dicionario dicionario = Dicionario.getInstance("op","func", "func", "reg", "r", "cond");        
        short result = dicionario.getType("add");
        assertEquals(0b1,result);
    }
    
}
