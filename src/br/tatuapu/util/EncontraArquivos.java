/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.util;

import br.tatuapu.model.ArquivoPalavrasValidadas;
import br.tatuapu.model.Site;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author tatuapu
 */
public class EncontraArquivos {
    public static ArquivoPalavrasValidadas[] PegaArquivosDo(Site site){
        String srcFile = Contexto.DATADIR;
        String fileNameBase = "palavrasvalidadasgoogle-"+site.getId();
        File arquivo = new File(srcFile);
        int count=0;
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
		String lowercaseName = name.toLowerCase();                    
                    if (lowercaseName.contains(fileNameBase)) {
					return true;
				} else {
					return false;
				}
			}
		};
        File raiz = arquivo;
        File readedFiles[] = raiz.listFiles(textFilter);
        ArquivoPalavrasValidadas[] a = new ArquivoPalavrasValidadas[readedFiles.length];
		for(File f: readedFiles) {
                    FileTime dataCriada;                    
                    if(f.isFile()) {
                        try {
                            BasicFileAttributes attr = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
                            dataCriada = attr.creationTime();
                        } catch (IOException ex) {
                            Logger.getLogger(EncontraArquivos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        //System.out.println(f.getName());  
                        java.util.Date alteradoDay = new java.util.Date(f.lastModified());
                        String alterado = new String(new SimpleDateFormat("dd-MM-yyyy").format(alteradoDay));
                        a[count] = new ArquivoPalavrasValidadas(site, pegaLetraDo(f), alterado);
                        count++;
                    }    
		}
        
        
        return a;
    }

    private static char pegaLetraDo(File f) {
        String[] subs = f.getName().split(Pattern.quote ("-"));
        String[] subs2= subs[subs.length - 1].split(Pattern.quote ("."));
        return subs2[0].charAt(0);
    }
}
