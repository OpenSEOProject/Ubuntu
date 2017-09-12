/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

import java.io.Serializable;

/**
 *
 * @author tatuapu
 */
public class Palavra  implements Serializable {
    private final String palavra;
    private final Integer pagina;
    private final Integer posicao;
    public Palavra(String palavra, Integer pagina, Integer posicao){
        this.palavra = palavra;
        this.pagina = pagina;
        this.posicao = posicao;
    }

    /**
     * @return the palavra
     */
    public String getPalavra() {
        return palavra;
    }

    /**
     * @return the pagina
     */
    public Integer getPagina() {
        return pagina;
    }

    /**
     * @return the posicao
     */
    public Integer getPosicao() {
        return posicao;
    }
    public String toString(){
        return this.palavra + "-"+this.posicao+"/"+this.pagina;
    }
}
