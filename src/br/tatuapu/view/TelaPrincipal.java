/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.view;

import br.tatuapu.model.TabelaSitesModel;
import java.awt.BorderLayout;
import javax.swing.*;
import static br.tatuapu.util.Contexto.*;
import br.tatuapu.model.*;
import br.tatuapu.util.SitesDados;
import java.awt.event.*;
import java.util.ArrayList;
import br.tatuapu.view.*;

/**
 *
 * @author tatuapu
 */
public class TelaPrincipal extends JFrame implements WindowFocusListener {
    
    private MenuAplicacao menuAplicacao;
    private JTable tabela;
    
    public TelaPrincipal(){
        super(APLICACAONOME +" - "+APLICACAOVERSAO);
        JPanel painel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	BorderLayout borda = new BorderLayout();
	painel.setLayout(borda);
         setSize(APLICACAOW,APLICACAOH);
        //centralizando
        setLocationRelativeTo(null);
        
        //setando o menu da janela
        this.menuAplicacao = new MenuAplicacao();
        setJMenuBar(this.menuAplicacao);  
        
        tabela = new JTable();
        JScrollPane barra =new JScrollPane(tabela);
        TabelaSitesModel modelo = new TabelaSitesModel();
        tabela.setModel(modelo);
        preencheTabela();
        
        JPanel painelCentral =new JPanel(new BorderLayout());
        painelCentral.add("Center",barra);
        painelCentral.add("North",new JLabel("Sites acompanhados"));
        painel.add("Center",painelCentral);
        
        //popup menu
        //Add listener to components that can bring up popup menus.
        MouseListener popupListener = new PopupListener();
        tabela.addMouseListener(popupListener);
        
        setContentPane(painel);
        addWindowFocusListener(this);
    }

    private void preencheTabela() {
        //criando um novo model para a tabela
            TabelaSitesModel modelo = new TabelaSitesModel();

            SitesDados sitesDados = new SitesDados();
            ArrayList<Site> sites = sitesDados.recuperaSitesAtivos();
            for(int i=0;i<sites.size();i++){//varrendo os dados salvos
                modelo.addRow(sites.get(i)); //add no model                    
            }
            tabela.setModel(modelo);
    }
    public JTable getTabela(){
        return this.tabela;
    }
    @Override
    public void windowGainedFocus(WindowEvent we) {
        preencheTabela();
    }

    @Override
    public void windowLostFocus(WindowEvent we) {
        //
    }
}
