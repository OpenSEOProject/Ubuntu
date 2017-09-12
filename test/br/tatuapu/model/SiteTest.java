/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

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
public class SiteTest {
    
    public SiteTest() {
    }
    
    

    /**
     * Test of getMainDomain method, of class Site.
     */
    @Test
    public void testGetMainDomain() {
        System.out.println("getMainDomain");
        Site instance = new Site("victor hugo","http://profvictorhugo.esy.es",0);
        
        String result = instance.getMainDomain();
        assertEquals("profvictorhugo", result);
        
        instance = new Site("victor hugo","http://www.profvictorhugo.esy.es",0);
        result = instance.getMainDomain();
        assertEquals("profvictorhugo", result);
        
        instance = new Site("victor hugo","https://www.profvictorhugo.esy.es",0);
        result = instance.getMainDomain();
        assertEquals("profvictorhugo", result);
        
        instance = new Site("victor hugo","http://sub.profvictorhugo.esy.es",0);
        result = instance.getMainDomain();
        assertEquals("profvictorhugo", result);
    }
    
}
