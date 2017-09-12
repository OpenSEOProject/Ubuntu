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
public class PainelPalavrasRanqueadas extends JPanel {
    
    JTable tabela;
    TabelaPalavrasModel tabelaPalavrasModel;
    PalavrasValidadasDados palavrasDados;
    final Site site;
    //private final JButton btnCarregarSearchConsole;
    
    public PainelPalavrasRanqueadas(Site site){
        this.site = site;
	JToolBar bar = new JToolBar();
	BorderLayout borda = new BorderLayout();
	setLayout(borda);        
        
        add("North",bar);
        
        //Tabela
        tabela = new JTable();
        JScrollPane  barraRolagem = new JScrollPane(tabela);
	add("Center",barraRolagem);
        tabelaPalavrasModel = new TabelaPalavrasModel();
        //tabelaPalavrasModel.addRow(new Palavra("Teste",1,1));
        tabela.setModel(tabelaPalavrasModel);
        
        preencheTabela();
    }
    
    public void preencheTabela(){
            //criando um novo model para a tabela
            TabelaPalavrasModel modelo = new TabelaPalavrasModel();

            palavrasDados = new PalavrasValidadasDados(this.site);
            ArrayList<Palavra> palavras = palavrasDados.recuperaPalavrasAtivas();
            for(int i=0;i<palavras.size();i++){//varrendo as comandas salvas
                modelo.addRow(palavras.get(i)); //add as comandas no model                    
            }
            tabela.setModel(modelo);
        }
}
