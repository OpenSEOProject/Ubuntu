/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

import br.tatuapu.util.DadosLoader;
import br.tatuapu.util.Encrypter;
import java.util.ArrayList;
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
public class DadosTest {
    static Dados instance = new Dados();
    
    public DadosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new Dados();
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
     * Test of login method, of class Dados.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String n = "admin";
        String p = "admin";
        instance.getUsuarios();
        Usuario expResult = null;
        Usuario result = instance.login(n, p);
        assertFalse(result == expResult);
        System.out.println(result.getGoogleEmail() + "-"+result.getGoogleSenha());
    }

    /**
     * Test of addUsuario method, of class Dados.
     */
//    @Test
//    public void testAddUsuario() {
//        System.out.println("addUsuario");
//        Usuario u = null;
//        Dados instance = new Dados();
//        instance.addUsuario(u);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    @Test
//    public void testAddUsuarioAdmin(){
//        System.out.println("addUsuario");
//        Dados d = new Dados();
//        d.getUsuarios();
//        int antes = d.getQtdUsuarios();
//        Usuario u = new Usuario("admin",Encrypter.encryptMD5("admin"));
//        u.setGoogleEmail("jm.tcc.seo@gmail.com");
//        u.setGoogleSenha("tccdeseo");
//        d.addUsuario(u);
//        assertEquals(antes+1,d.getQtdUsuarios());
//    }
    @Test
    public void testGetUsuarios(){
        System.out.println(instance.getQtdUsuarios());
        instance.getUsuarios();
        System.out.println(instance.getQtdUsuarios());
        assertTrue(instance.getQtdUsuarios()>0);
        
    }
    
}
