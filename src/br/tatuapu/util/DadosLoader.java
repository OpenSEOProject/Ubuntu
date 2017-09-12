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
public class DadosLoader {

    private static final String srcFile = Contexto.DATADIR+"sistema.dat";
    
    public static void salvaDados(Dados d){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream(srcFile);
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

	    //Grava o objeto Dados no arquivo
	    objGravar.writeObject(d);
	    objGravar.flush();

	    objGravar.close();
	    arquivoGrav.flush();

	    arquivoGrav.close();
	}catch(Exception e){
            e.printStackTrace();
	}
    }
    public static Dados recuperaDados(){        
        Dados comRecovered=null;
        try{
            File arquivo = new File(srcFile);
            if(!arquivo.exists()){
                salvaDados(new Dados());
            }
            //Carrega o arquivo
            FileInputStream arquivoLeitura = new FileInputStream(srcFile);

            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            comRecovered = (Dados) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
	}catch(Exception e){
            e.printStackTrace();
	}
        return comRecovered;
    }
    
}
