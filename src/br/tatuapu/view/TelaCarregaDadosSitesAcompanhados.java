package br.tatuapu.view;

import br.tatuapu.controller.Executor;
import br.tatuapu.model.*;
import static br.tatuapu.util.Contexto.*;
import br.tatuapu.util.*;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author 1st version tatuapu
 */
public class TelaCarregaDadosSitesAcompanhados extends JFrame implements WindowFocusListener {
    
    public static final String MODULOCOD="telaCarregaDadosSitesAcompanhados";
    public static final String MODULONM="Carregador de dados de sites";
    private JTabbedPane jtpTabs;
    
    public TelaCarregaDadosSitesAcompanhados(){
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
        addWindowFocusListener(this);
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
                    PainelPalavrasGoogleSearchConsole(
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
