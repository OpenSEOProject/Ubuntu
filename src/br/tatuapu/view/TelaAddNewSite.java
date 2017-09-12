/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.view;

import br.tatuapu.model.Site;
import br.tatuapu.model.TabelaSitesModel;
import static br.tatuapu.util.Contexto.*;
import br.tatuapu.util.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author tatuapu
 */
public class TelaAddNewSite extends JFrame {

    public static final String MODULOCOD = "telaAddNewSite";
    public static final String MODULONM = "Cadastro de sites para companhamento";

    JTable tabela;
    private final JTextField tPaginaNome;
    private final JTextField tUrl;
    private SitesDados sitesDados;

    public TelaAddNewSite() {
        super(APLICACAONOME + " - " + MODULONM);
        JPanel painel = new JPanel();
        JToolBar bar = new JToolBar();
        bar.setFloatable(false);
        BorderLayout borda = new BorderLayout();
        painel.setLayout(borda);
        setSize(APLICACAOW, APLICACAOH);
        //centralizando
        setLocationRelativeTo(null);

        //elementos da barra de ferramentas
        JLabel lPaginaNome = new JLabel("Nome da Página:");
        bar.add(lPaginaNome);

        tPaginaNome = new JTextField(15);
        bar.add(tPaginaNome);

        JLabel lUrl = new JLabel("URL (Search Console):");
        bar.add(lUrl);

        tUrl = new JTextField(15);
        bar.add(tUrl);

        JButton btnMais = new JButton(" + ");
        btnMais.setToolTipText("Cadastrar site para acompanhamento");
        bar.add(btnMais);

        JButton btnMenos = new JButton(" - ");
        btnMenos.setToolTipText("Remover site do acompanhamento");
        bar.add(btnMenos);

        painel.add("North", bar);

        //
        tabela = new JTable();
        JScrollPane barraRolagem = new JScrollPane(tabela);
        painel.add("Center", barraRolagem);
        TabelaSitesModel modelo = new TabelaSitesModel();
        tabela.setModel(modelo);

        setContentPane(painel);
        preencheTabela();

        //ações
        btnMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaisActionPerformed();
            }
        });
        btnMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosActionPerformed();
            }
        });
    }

    public void btnMenosActionPerformed() {
        //obtendo o model da tabela
        TabelaSitesModel modelo = (TabelaSitesModel) this.tabela.getModel();
        int i = tabela.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(null, "Nenhum site selecionado!");
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma remoção?");
            if (opcao == 0 || opcao == -1) {
                modelo.removeRow(i);
                salvarDados();
            }
        }
    }

    public void btnMaisActionPerformed() {
        SitesDados sD = new SitesDados();
        Boolean checa = true;
        if (tPaginaNome.getText().equals("")) {
            checa = false;
            tPaginaNome.requestFocus();
            JOptionPane.showMessageDialog(null, "Preencha corretamente o nome!");
        } else if (tUrl.getText().equals("")) {
            checa = false;
            tUrl.requestFocus();
            JOptionPane.showMessageDialog(null, "Preencha corretamente a URL!");
        }
        if (checa) {
            if (sD.contaSites() <= 5) {
                TabelaSitesModel modelo = (TabelaSitesModel) tabela.getModel();
                modelo.addRow(new Site(tPaginaNome.getText(), tUrl.getText(), sD.contaSites()));
                salvarDados();
                Forms.clearTextFields(getContentPane());
            } else {
                JOptionPane.showMessageDialog(null, "Limite de 5 sites!");
            }
        }
    }

    public void salvarDados() {
        TabelaSitesModel modelo = (TabelaSitesModel) tabela.getModel();
        SitesDados salvador = new SitesDados();
        salvador.salvaSitesAtivos(modelo.getDados());
    }

    public void preencheTabela() {
        //criando um novo model para a tabela
        TabelaSitesModel modelo = new TabelaSitesModel();

        sitesDados = new SitesDados();
        ArrayList<Site> sites = sitesDados.recuperaSitesAtivos();
        for (int i = 0; i < sites.size(); i++) {//varrendo os dados salvos
            modelo.addRow(sites.get(i)); //add no model                    
        }
        tabela.setModel(modelo);
    }
}
