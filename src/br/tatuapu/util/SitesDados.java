/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.util;

import br.tatuapu.model.Site;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author tatuapu
 */
public class SitesDados {
    public Integer contaSites(){
        return recuperaSitesAtivos().size();
    }
    public void salvaSitesAtivos(ArrayList<Site> sites){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream("sitesAtivos.dat");
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto sites no arquivo
	    objGravar.writeObject(sites);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    public ArrayList<Site> recuperaSitesAtivos(){
        ArrayList<Site> comRecovered = null;
        try{
            File arquivo = new File("sitesAtivos.dat");
            if(!arquivo.exists()){
                salvaSitesAtivos(new ArrayList<Site>());
            }    
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream("sitesAtivos.dat");

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            comRecovered = (ArrayList<Site>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(Exception e){
            e.printStackTrace();
	}
        return comRecovered;
    }
}
