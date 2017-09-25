/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.util;

import br.tatuapu.model.ArquivoPalavrasValidadas;
import br.tatuapu.model.Site;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tatuapu
 */
public class EncontraArquivosTest {
    
    public EncontraArquivosTest() {
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
     * Test of PegaArquivosDo method, of class EncontraArquivos.
     */
    @Test
    public void testPegaArquivosDo() {
        System.out.println("PegaArquivosDo");
        Site site = null;
        ArquivoPalavrasValidadas[] expResult = null;
        ArquivoPalavrasValidadas[] result = EncontraArquivos.PegaArquivosDo(site);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
