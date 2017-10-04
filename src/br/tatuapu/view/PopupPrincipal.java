/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.view;

import br.tatuapu.controller.Executor;
import br.tatuapu.model.ArquivoPalavrasValidadas;
import br.tatuapu.model.Palavra;
import br.tatuapu.model.Ranking;
import br.tatuapu.model.Site;
import br.tatuapu.model.TabelaSitesModel;
import br.tatuapu.util.BotBuscador;
import br.tatuapu.util.EncontraArquivos;
import br.tatuapu.util.PageRankAnalytic;
import br.tatuapu.util.PalavrasValidadasDados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author tatuapu
 */
public class PopupPrincipal extends JPopupMenu {
    JMenuItem miValidaPalavras;
    JMenuItem miSearchBot;
    public PopupPrincipal(){
        this.miValidaPalavras =new JMenuItem("Valida palavras");
        add(miValidaPalavras);
        this.miValidaPalavras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente iniciar?");
                if (opcao == 0 || opcao == -1) {
                    iniciaProcessamento();
                }
            }
        });
        this.miSearchBot = new JMenuItem("Bot buscador");
        add(miSearchBot);
        this.miSearchBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente iniciar?");
                if (opcao == 0 || opcao == -1) {
                    iniciaBot();
                }
            }
        });
    }
    private void iniciaBot(){
        TelaPrincipal telaPrincipal = (TelaPrincipal) Executor.getTelaPrincipal();
        int id = telaPrincipal.getTabela().getSelectedRow();
        //garantindo que selecionou-se um item da tabela
        if(id>=0){
            TabelaSitesModel modelo = (TabelaSitesModel) telaPrincipal.getTabela().getModel();
            Site site = modelo.getRowAt(id);
            try{
                new BotBuscador(site,5);
            }catch(Exception e){
                if(e.getMessage().equals("Sem palavras salvas")){
                    JOptionPane.showMessageDialog(null,"Não existem palavras para processar");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Selecione um site da lista");
        }  
        
    }
    private void iniciaProcessamento(){
        TelaPrincipal telaPrincipal = (TelaPrincipal) Executor.getTelaPrincipal();
        int id = telaPrincipal.getTabela().getSelectedRow();
        //garantindo que selecionou-se um item da tabela
        if(id>=0){
            TabelaSitesModel modelo = (TabelaSitesModel) telaPrincipal.getTabela().getModel();
            Site site = modelo.getRowAt(id);
            try{
                ArrayList<Palavra> lista = PageRankAnalytic.getPageRank(site);
                System.out.println(lista.size());
                
                ArquivoPalavrasValidadas[] a = EncontraArquivos.PegaArquivosDo(site);
                char u = (a.length>0) ? (char) a.length : 'a';
                
                PalavrasValidadasDados pvd = new PalavrasValidadasDados(site,u);
                pvd.salvaPalavrasAtivas(lista);
                JOptionPane.showMessageDialog(null,"Palavras validadas com sucesso!");
            }catch(Exception e){
                if(e.getMessage().equals("Sem palavras salvas")){
                    JOptionPane.showMessageDialog(null,"Não existem palavras para processar");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Selecione um site da lista");
        }    
    }
}

class PopupListener extends MouseAdapter {
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        PopupPrincipal menu = new PopupPrincipal();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}