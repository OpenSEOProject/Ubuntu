package br.tatuapu.view;

import br.tatuapu.controller.Executor;
import br.tatuapu.model.Palavra;
import static br.tatuapu.util.Contexto.*;
import java.awt.BorderLayout;
import javax.swing.*;
import br.tatuapu.model.*;
import br.tatuapu.util.*;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author tatuapu
 */
public class PainelPalavrasRanqueadas extends JPanel {
    
    JTable tabela;
    TabelaPalavrasModel tabelaPalavrasModel;
    PalavrasValidadasDados palavrasDados;
    ArquivoPalavrasComboBoxModel arquivoPalavrasComboBoxModel;
    
    final Site site;
    //private final JButton btnCarregarSearchConsole;
    private final JComboBox<Object> jcArquivoPalavras;
    private final JButton bCarregaDados;
    
    public PainelPalavrasRanqueadas(Site site){
        this.site = site;
	JToolBar bar = new JToolBar();
	BorderLayout borda = new BorderLayout();
	setLayout(borda);        
        
        jcArquivoPalavras = new JComboBox<>();
        bCarregaDados = new JButton("Listar");
        bar.add(jcArquivoPalavras);
        bar.add(bCarregaDados);
        bar.setFloatable(false);
        
        arquivoPalavrasComboBoxModel = new ArquivoPalavrasComboBoxModel(this.site);
        jcArquivoPalavras.setModel(arquivoPalavrasComboBoxModel);
        //arquivoPalavrasComboBoxModel.addRow(new ArquivoPalavrasValidadas(site, 'a', "hoje"));
        preencheJCArquivoPalavras();
        add("North",bar);
        
        //Tabela
        tabela = new JTable();
        JScrollPane  barraRolagem = new JScrollPane(tabela);
	add("Center",barraRolagem);
        tabelaPalavrasModel = new TabelaPalavrasModel();
        //tabelaPalavrasModel.addRow(new Palavra("Teste",1,1));
        tabela.setModel(tabelaPalavrasModel);
        
        //preencheTabela();
        bCarregaDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArquivoPalavrasValidadas a = (ArquivoPalavrasValidadas) jcArquivoPalavras.getSelectedItem();
                
                preencheTabela(a.getId());
            }
        });
    }
    
    public void preencheTabela(char id){
            //criando um novo model para a tabela
            TabelaPalavrasModel modelo = new TabelaPalavrasModel();

            palavrasDados = new PalavrasValidadasDados(this.site,id);
            ArrayList<Palavra> palavras = palavrasDados.recuperaPalavrasAtivas();
            for(int i=0;i<palavras.size();i++){//varrendo as comandas salvas
                modelo.addRow(palavras.get(i)); //add as comandas no model                    
            }
            tabela.setModel(modelo);
        }

    private void preencheJCArquivoPalavras() {
        ArquivoPalavrasValidadas[] a = EncontraArquivos.PegaArquivosDo(site);
        for(ArquivoPalavrasValidadas s:a){
            arquivoPalavrasComboBoxModel.addRow(s);
        }
    }
}
