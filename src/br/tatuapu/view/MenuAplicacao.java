/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.view;

import br.tatuapu.controller.Executor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author tatuapu
 */
public class MenuAplicacao extends JMenuBar {
    private final JMenu menuAplicacao, menuAcoes;
    private final JMenuItem miSair, miCarregarPalavrasGoogleConsole;
    private final JMenuItem miAddNewSite, miCarregaPalavrasRanqueadas;

    public MenuAplicacao() {
        menuAplicacao = new JMenu("Aplicação");
        menuAplicacao.setMnemonic('A');
        add(menuAplicacao);
        
        miSair = new JMenuItem("Sair");
        miSair.setMnemonic('S');
        menuAplicacao.add(miSair);
        
        menuAcoes = new JMenu("Ações");
        menuAcoes.setMnemonic('e');
        add(menuAcoes);
        
        miCarregarPalavrasGoogleConsole = new JMenuItem("Carregar do Search Console");
        miCarregarPalavrasGoogleConsole.setMnemonic('C');
        menuAcoes.add(miCarregarPalavrasGoogleConsole);
        
        miAddNewSite = new JMenuItem("Add novo site");
        miAddNewSite.setMnemonic('n');
        menuAcoes.add(miAddNewSite);
        
        miCarregaPalavrasRanqueadas = new JMenuItem("Palavras Ranqueadas");
        menuAcoes.add(miCarregaPalavrasRanqueadas);
        
        
        
        /*Evento do menuItem Sair*/
        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente Sair?");
                if (opcao == 0 || opcao == -1) {
                    System.exit(0);
                }
            }
        });
        /*Evento do menuItem Carregar Palavras google console*/
        miCarregarPalavrasGoogleConsole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Executor.getTelaCarregaDadosSitesAcompanhados()==null){
                    Executor.setTelaCarregaDadosSitesAcompanhados(new TelaCarregaDadosSitesAcompanhados());
                }
                Executor.getTelaCarregaDadosSitesAcompanhados().setVisible(true);
            }
        });
        /*Evento do menuItem Carregar Palavras google console*/
        miCarregaPalavrasRanqueadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Executor.getTelaCarregaPalavrasRanqueadas()==null){
                    Executor.setTelaCarregaPalavrasRanqueadas(new TelaCarregaPalavrasRanqueadas());
                }
                Executor.getTelaCarregaPalavrasRanqueadas().setVisible(true);
            }
        });
        /*Evento do menuItem Carregar Palavras google console*/
        miAddNewSite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (Executor.getTelaAddNewSite()==null){
                    Executor.setTelaAddNewSite(new TelaAddNewSite());
                }
                Executor.getTelaAddNewSite().setVisible(true);
            }
        });
    }
}
