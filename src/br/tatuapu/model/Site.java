/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 *
 * @author tatuapu
 */
public class Site implements Serializable {
    private static Integer contador=0;
    private final Integer id;
    private final String paginaNome;
    private final String urlSearchConsole;
    
    /**
     * 
     * @param pn nome do site
     * @param url url do site, segundo google search console
     * @param c número de sites já criados
     */
    public Site(String pn, String url, Integer c){
        if(c==null)
            contador++;
        else
            contador=++c;
        id=contador;
        paginaNome = pn;
        urlSearchConsole= url;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the paginaNome
     */
    public String getPaginaNome() {
        return paginaNome;
    }

    /**
     * @return the urlSearchConsole
     */
    public String getUrlSearchConsole() {
        return urlSearchConsole;
    }

    public String getMainDomain() {
        String retorno = "";
        if(urlSearchConsole.toLowerCase().contains("www")){
            String[] quebrado = urlSearchConsole.split(Pattern.quote("."));
            retorno = quebrado[1];
        }else{
            String[] quebrado = urlSearchConsole.split(Pattern.quote("."));
            if(quebrado.length<=3){
                if(quebrado[0].length()<=3){
                    retorno = urlSearchConsole;
                }else{
                    if(quebrado[0].toLowerCase().contains("http://")){
                        String[] quebrado2 = quebrado[0].split(Pattern.quote("http://"));
                        retorno = quebrado2[1];
                    }else if(quebrado[0].toLowerCase().contains("https://")){
                        String[] quebrado2 = quebrado[0].split(Pattern.quote("http://"));
                        retorno = quebrado2[1];
                    }else    
                        retorno = quebrado[0];
                }
            }else
                retorno = quebrado[1];
        } 
        return retorno;
    }
}
