package br.tatuapu.view;

import br.tatuapu.model.Palavra;
import static br.tatuapu.util.Contexto.*;
import java.awt.BorderLayout;
import javax.swing.*;
import br.tatuapu.model.*;
import br.tatuapu.util.*;
import java.awt.Cursor;
import java.util.ArrayList;

/**
 *
 * @author tatuapu
 */
public class PainelPalavrasGoogleSearchConsole extends JPanel {
    
    JTable tabela;
    TabelaPalavrasModel tabelaPalavrasModel;
    PalavrasDados palavrasDados;
    final Site site;
    private final JButton btnCarregarSearchConsole;
    
    public PainelPalavrasGoogleSearchConsole(Site site){
        this.site = site;
	JToolBar bar = new JToolBar();
	BorderLayout borda = new BorderLayout();
	setLayout(borda);
        
        //elementos da barra de ferramentas
        JButton btnCarregaDadosSalvos = new JButton("Carregar do arquivo");
        btnCarregaDadosSalvos.setToolTipText("Carregar palavras já salvas em arquivo");
        bar.add(btnCarregaDadosSalvos);
        
        btnCarregarSearchConsole = new JButton("Carregar do Search Console");
        btnCarregarSearchConsole.setToolTipText("Carregar palavras do Sistema do Google Search Console");
        bar.add(btnCarregarSearchConsole); 
        
        JButton btnSalvar = new JButton("Salvar");
        bar.add(btnSalvar);
        
        add("North",bar);
        
        //Tabela
        tabela = new JTable();
        JScrollPane  barraRolagem = new JScrollPane(tabela);
	add("Center",barraRolagem);
        tabelaPalavrasModel = new TabelaPalavrasModel();
        //tabelaPalavrasModel.addRow(new Palavra("Teste",1,1));
        tabela.setModel(tabelaPalavrasModel);
        
        //ações
        btnCarregaDadosSalvos.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
				btnCarregaDadosSalvosActionPerformed();
            }
	});
        btnCarregarSearchConsole.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
				btnCarregaDadosSearchConsoleActionPerformed();
            }
	});
        
        btnSalvar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
				btnSalvarActionPerformed();
            }
	});
        
        preencheTabela();
    }
    void btnCarregaDadosSalvosActionPerformed(){
        preencheTabela();
        JOptionPane.showMessageDialog(null,"Dados carregados do arquivo com sucesso!");
    }
    void btnCarregaDadosSearchConsoleActionPerformed(){
        //o cabra que vai buscar os dados no search console
        CarregadorDadosSearchConsole carregador = new CarregadorDadosSearchConsole(this.site);
        //criando um novo model para a tabela
        TabelaPalavrasModel modelo = new TabelaPalavrasModel();
        //varrendo a lista de palavras e mandando pro model da tabela
        JOptionPane.showMessageDialog(null,"Dados serão caregados da Web. Aguarde o "
                + "completo carregamento para prosseguir");
        //btnCarregarSearchConsole.setEnabled(false);
        //btnCarregarSearchConsole.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ArrayList<String> palavras = carregador.capturaPalavras();
            for(int i=0;i<palavras.size();i++){//varrendo as comandas salvas
                modelo.addRow(new Palavra(palavras.get(i),0,0)); //add as comandas no model                    
            }
            tabela.setModel(modelo);
        //salvando os dados em arquivo
        this.btnSalvarActionPerformed();
        //btnCarregarSearchConsole.setEnabled(true);
        //setCursor(Cursor.getDefaultCursor());
    }
    void btnSalvarActionPerformed(){
        //obtendo o model da tabela
	TabelaPalavrasModel modelo = (TabelaPalavrasModel) tabela.getModel();
        
        ArrayList<Palavra> palavras = modelo.getDados();
        palavrasDados.salvaPalavrasAtivas(palavras);
        JOptionPane.showMessageDialog(null,"Dados salvos com sucesso!");
    }
    public void preencheTabela(){
            //criando um novo model para a tabela
            TabelaPalavrasModel modelo = new TabelaPalavrasModel();

            palavrasDados = new PalavrasDados(this.site);
            ArrayList<Palavra> palavras = palavrasDados.recuperaPalavrasAtivas();
            for(int i=0;i<palavras.size();i++){//varrendo as comandas salvas
                modelo.addRow(palavras.get(i)); //add as comandas no model                    
            }
            tabela.setModel(modelo);
        }
}
