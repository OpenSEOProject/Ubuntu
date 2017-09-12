/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

import br.tatuapu.util.Encrypter;
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
public class UsuarioTest {
    
    public UsuarioTest() {
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
     * Test of getNomeUsuario method, of class Usuario.
     */
//    @Test
//    public void testGetNomeUsuario() {
//        System.out.println("getNomeUsuario");
//        Usuario instance = null;
//        String expResult = "";
//        String result = instance.getNomeUsuario();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getSenha method, of class Usuario.
     */
    @Test
    public void testGetSenha() throws Exception {
        System.out.println("getSenha");
        Usuario instance = new Usuario("User 1",Encrypter.encryptMD5("123456"));
        String result = instance.getSenha();
        System.out.println(result);
        assertFalse(result.equals("123456"));
    }

    /**
     * Test of comparaSenha method, of class Usuario.
     */
    @Test
    public void testComparaSenha() throws Exception {
        System.out.println("comparaSenha");
        String s = "";
        Usuario instance = new Usuario("User 1",Encrypter.encryptMD5("123456"));
        assertTrue(instance.comparaSenha("123456"));
    }
    
}
