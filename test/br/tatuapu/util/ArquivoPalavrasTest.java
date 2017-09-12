/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.util;

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
public class ArquivoPalavrasTest {
    
    public ArquivoPalavrasTest() {
    }
    
    

    /**
     * Test of salvaPalavras method, of class ArquivoPalavras.
     */
    @Test
    public void testSalvaPalavras() {
        System.out.println("salvaPalavras");
        ArrayList<String> palavras = new ArrayList<String>();
        palavras.add(new String("rebinboca"));
        palavras.add(new String("GUI Java - Como Criar menus e submenus"));
        palavras.add(new String("Primeiros passos swing java"));
        palavras.add(new String("escopo de variáveis globais"));
        palavras.add(new String("tutorial google trends"));
        palavras.add(new String("criar view mysql com parâmetros"));
        palavras.add(new String("jm7087"));
        palavras.add(new String("Descobrir palavras fortes"));
        palavras.add(new String("definindo público para campanhas"));
        palavras.add(new String("erro git reject"));
        palavras.add(new String("tutorial git"));
        palavras.add(new String("jm 7087"));
        palavras.add(new String("séries 7087"));
        palavras.add(new String("tutorials 7087"));
        palavras.add(new String("as seis lições audio"));
        palavras.add(new String("como pagar fatura nubank"));
        palavras.add(new String("7087 theory"));
        palavras.add(new String("hulk onde os monstros habitam"));
        
        ArquivoPalavras.salvaPalavras(palavras);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    
}
