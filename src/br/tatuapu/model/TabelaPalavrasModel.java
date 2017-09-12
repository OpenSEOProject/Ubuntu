/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author tatuapu
 */
public class TabelaPalavrasModel extends AbstractTableModel {
    
    private ArrayList<Palavra> dados;
    private String[] colunas = {"Palavra","Página","Posição"};
    
    public ArrayList<Palavra> getDados(){
        return this.dados;
    }
    
    public TabelaPalavrasModel(){
         this.dados = new ArrayList<>();
    }

    public void addRow(Palavra palavra){
        this.dados.add(palavra);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
            this.dados.remove(linha);
            this.fireTableRowsDeleted(linha,linha);
    }
    
    public String getColumnName(int num){
            return this.colunas[num];
    }
    
    @Override
    public int getRowCount() {
        return this.dados.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
                    case 0: return dados.get(linha).getPalavra();
                    case 1: return dados.get(linha).getPagina();
                    case 2: return dados.get(linha).getPosicao();
            }
            return null;
    }

    public void setDados(ArrayList<Palavra> palavras) {
        this.dados = palavras;
    }
    
}
