/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

/**
 *
 * @author tatuapu
 */
public class ArquivoPalavrasValidadas {
    private final Site site;
    private final char id;
    private final String dateCreated;
    
    public ArquivoPalavrasValidadas(Site site, char id, String dateCreated){
        this.site = site;
        this.id = id;
        this.dateCreated = dateCreated;
    }

    /**
     * @return the site
     */
    public Site getSite() {
        return site;
    }

    /**
     * @return the id
     */
    public char getId() {
        return id;
    }

    /**
     * @return the dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }
    public String toString(){
        return this.id + " - " + this.dateCreated;
    }
}
