/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.util;

import br.tatuapu.model.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author tatuapu
 */
public class PalavrasValidadasDados {
    private final Site site;
    private final String srcFile;
    private final char id;
    public PalavrasValidadasDados(Site site, char id){
        this.site = site;
        this.id = id;
        this.srcFile = Contexto.DATADIR+"palavrasValidadasGoogle-"+this.site.getId()+"-"+this.id+".dat";
    }
    public void salvaPalavrasAtivas(ArrayList<Palavra> pal){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream(this.srcFile);
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto cliente no arquivo
	    objGravar.writeObject(pal);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    public ArrayList<Palavra> recuperaPalavrasAtivas(){        
        ArrayList<Palavra> comRecovered = null;
        try{
            File arquivo = new File(this.srcFile);
            if(arquivo.exists()){
                //salvaPalavrasAtivas(new ArrayList<Palavra>());
                //Carrega o arquivo
                FileInputStream arquivoLeitura = new FileInputStream(this.srcFile);

                //Classe responsavel por recuperar os objetos do arquivo
                ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
                comRecovered = (ArrayList<Palavra>) objLeitura.readObject();
                objLeitura.close();
                arquivoLeitura.close();
            }else{
                comRecovered = new ArrayList<Palavra>();
            }
            
	}catch(Exception e){
            e.printStackTrace();
	}
        return comRecovered;
    }
}
