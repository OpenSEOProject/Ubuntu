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
public class TabelaSitesModel extends AbstractTableModel {
    
    private ArrayList<Site> dados;
    private String[] colunas = {"id","Página","URL-Search-Console"};
    
    public ArrayList<Site> getDados(){
        return this.dados;
    }
    
    public TabelaSitesModel(){
         this.dados = new ArrayList<Site>();
    }

    public void addRow(Site site){
        this.dados.add(site);
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
                    case 0: return dados.get(linha).getId();
                    case 1: return dados.get(linha).getPaginaNome();
                    case 2: return dados.get(linha).getUrlSearchConsole();
            }
            return null;
    }

    public Site getRowAt(int linha){
        return this.dados.get(linha);
    }
    
}
