/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.util;

import br.tatuapu.model.Palavra;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author tatuapu
 */
public class ArquivoPalavras {
    private static final String srcFile = Contexto.DATADIR+"palavrasAtivas.dat";
    /**
     * Trata das palavras para ação evasiva
     * @param palavras 
     */
    public static void salvaPalavras(ArrayList<String> palavras){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream(srcFile);
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto mesas no arquivo
	    objGravar.writeObject(palavras);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    
    /**
     * Trata das palavras carregadas do google search console
     * @param palavras 
     */
    public static void salvaPalavrasGoogle(ArrayList<String> palavras){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream(srcFile);
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto palavras no arquivo
	    objGravar.writeObject(palavras);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    
    /**
     * recupera palavras ação evasiva
     * @return 
     */
    public static ArrayList<String> recuperaPalavras(){
        ArrayList<String> palavrasRecovered = null;
        try{
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream("palavrasAtivas.dat");

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            palavrasRecovered = (ArrayList<String>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(Exception e){
            e.printStackTrace();
	}
        return palavrasRecovered;
    }
    /**
     * recupera palavras do google search console
     * @return 
     */
    public static ArrayList<String> recuperaPalavrasGoogle(){
        ArrayList<String> palavrasRecovered = null;
        try{
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream("palavrasAtivasGoogle.dat");

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            palavrasRecovered = (ArrayList<String>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(Exception e){
            e.printStackTrace();
	}
        return palavrasRecovered;
    }
}
