/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.view;

import br.tatuapu.model.Site;
import static br.tatuapu.util.Contexto.*;
import br.tatuapu.util.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author tatuapu
 */
public class TelaCarregaPalavrasRanqueadas  extends JFrame implements WindowFocusListener {
    
    public static final String MODULOCOD="telaCarregaPalavrasRanqueadas";
    public static final String MODULONM="Carregador de Palavras Ranqueadas";
    private JTabbedPane jtpTabs;
    
    public TelaCarregaPalavrasRanqueadas(){
        super(APLICACAONOME +" - "+MODULONM);
        init();
    }

    private void init() {        
        JPanel painel = new JPanel();
        BorderLayout borda = new BorderLayout();
	painel.setLayout(borda);
        setSize(APLICACAOW,APLICACAOH);
        //centralizando
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //abas
        criaAbas();
        
        painel.add("Center",jtpTabs);
        
        setContentPane(painel);
        //addWindowFocusListener(this);
    }

    public void criaAbas(){
        if(jtpTabs == null)
            jtpTabs = new JTabbedPane();
        else
            jtpTabs.removeAll();
        
        SitesDados sitesDados = new SitesDados();
        ArrayList<Site> dadosSites = sitesDados.recuperaSitesAtivos();        
        
        JPanel[] sitesAcompanhados = new JPanel[dadosSites.size()];
        for(int i=0;i<dadosSites.size();i++){
            sitesAcompanhados[i]= new 
                    PainelPalavrasRanqueadas(
                        dadosSites.get(i)
                    );
            jtpTabs.addTab(dadosSites.get(i).getPaginaNome(), sitesAcompanhados[i]);
        }
    }
    
    @Override
    public void windowGainedFocus(WindowEvent we) {
        criaAbas();
    }

    @Override
    public void windowLostFocus(WindowEvent we) {
        //
    }
    
}
