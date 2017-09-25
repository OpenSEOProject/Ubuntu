/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author tatuapu
 */
public class ArquivoPalavrasComboBoxModel extends AbstractListModel implements ComboBoxModel {
    private final Site site;
    private ArrayList<ArquivoPalavrasValidadas> dados;
    ArquivoPalavrasValidadas selection = null;
    
    public ArquivoPalavrasComboBoxModel(Site site){
        this.site = site;
        this.dados = new ArrayList<ArquivoPalavrasValidadas>();
    }

    public void addRow(ArquivoPalavrasValidadas p){
      this.dados.add(p);
      this.fireContentsChanged(p, 0, 0);
    }
    @Override
    public int getSize() {
        return this.dados.size();
    }

    @Override
    public Object getElementAt(int index) {
        return dados.get(index);
    }
    public void clearAll(){
      this.dados = new ArrayList<ArquivoPalavrasValidadas>();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        ArquivoPalavrasValidadas p = (ArquivoPalavrasValidadas) anItem;
        selection = p;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
