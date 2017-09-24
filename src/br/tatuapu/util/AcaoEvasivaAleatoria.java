package br.tatuapu.util;

import java.util.ArrayList;
import java.util.List;
import br.tatuapu.util.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author tatuapu
 */
public class AcaoEvasivaAleatoria {
    private static List<String> palavrasIniciais;
    private static WebDriver driver;
    public AcaoEvasivaAleatoria(){
        palavrasIniciais = ArquivoPalavras.recuperaPalavras();
    }
    public AcaoEvasivaAleatoria(WebDriver d) throws Exception{
        driver = d;
        //buscando uma lista pré-definida de palavras aleatórias para busca
        palavrasIniciais = ArquivoPalavras.recuperaPalavras();
        int chaveAleatoria = new Random().nextInt(palavrasIniciais.size());
        //encontrando uma palavra aleatória da lista carregada
        String pal = palavrasIniciais.get(chaveAleatoria);
        
        //dando um tempo pra despistar
        aguarda(20000);
        //abrindo um link qualquer da última busca realizada, caso haja
        if(Base.existsElement(driver, By.className("g"))){
            List<WebElement> linhas = driver.findElements(By.className("g"));
            int cont=0;
            for(int ii=0;ii<linhas.size();ii++){
                try{
                    if(Base.existsElement(driver, By.tagName("a"))){
                        List<WebElement> linhas2= linhas.get(ii).findElements(By.tagName("a"));
                        if(linhas2.size()>0){
                            linhas2.get(0).click();
                            cont++;
                        }    
                        aguarda(10000);
                        driver.navigate().back();
                        if(cont>=2) break;
                    } 
                }catch(Exception e){
                    //throw new Exception(e);
                }    
            }        
                
        }
        
        driver.get("http://www.profvictorhugo.esy.es");        
        aguarda(10000);
        navegaEm(driver,3);
        aguarda(1000);
        driver.get("https://www.google.com.br/search?q="+pal);
    }
    private void setDriver(WebDriver d){
        driver = d;
    }
    public static void aguarda(int i){
        //aguardando um tempo (i) antes de qualquer ação
        try{
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoEvasivaAleatoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param driver: entrada o driver aberto do navegador em questão
     * @param i : inteiro do número de links para navegar neste site
     */
    public void navegaEm(WebDriver driver, int vezes) {
        List<WebElement> links;
        String[] seguir = new String[vezes];
        int contador = 0;
        //minimizando erros, só abrindo navegação se houverem links no destino
        if(Base.existsElement(driver, By.tagName("a"))){
            links = driver.findElements(By.tagName("a"));
            //garantindo a execução somente até o máximo de links encontrados
            //coletando os links possíveis
            int chaveAleatoria = new Random().nextInt(links.size());
            for(int i=chaveAleatoria;i<links.size();i++){
                if(contador>=vezes) break;//se há mais links que esperado, só lemos a qtd definida
                
                seguir[contador] = links.get(i).getAttribute("href").toString();
                contador++;
            }
            //seguindo os "vezes" links
            for(int ii=0;ii<seguir.length;ii++){
                driver.get(seguir[ii]);
                aguarda(10000);
                driver.navigate().back();
            }
        }            
    }
}
