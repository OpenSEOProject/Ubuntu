/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.util;

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
public class EncrypterTest {
    
    public EncrypterTest() {
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
     * Test of encrypt method, of class Encrypter.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt");
        String str = "123456";
        Encrypter instance = new Encrypter();
        String expResult = "123456";
        String result = instance.encrypt(str);
        System.out.println(result);
        assertNotEquals(expResult, result);
        
    }

    /**
     * Test of decrypt method, of class Encrypter.
     */
    @Test
    public void testDecrypt() {
        System.out.println("decrypt");
        String str = "MTIzNDU2";
        Encrypter instance = new Encrypter();
        String expResult = "123456";
        String result = instance.decrypt(str);
        assertEquals(expResult, result);
    }
    
}
