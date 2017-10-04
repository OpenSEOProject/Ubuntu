/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.util;

import br.tatuapu.model.ArquivoPalavrasValidadas;
import br.tatuapu.model.Palavra;
import br.tatuapu.model.Site;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author tatuapu
 */
public class BotBuscador {
    private static int contador;
    private static int linhasLidas;
    private static WebDriver driver;
    static Site site;
    private static AcaoEvasivaAleatoria acaoEvasiva;
    private static ArrayList<Palavra> palavras;
    private static ArrayList<Palavra> palavrasEmbaralhadas;
    private static int contaRodadas;
    
    public BotBuscador(Site site, int rodadas) throws Exception {
        BotBuscador.site = site;
        contaRodadas = rodadas;
        acaoEvasiva = new AcaoEvasivaAleatoria();
        linhasLidas=0;
        palavras = new ArrayList<Palavra>();
        for(int i=0;i<rodadas;i++)
            navegaPelasPalavras();        
    }
    
    private static void setaPalavrasAleatorias(int tamanho){
        ArrayList<Palavra> p2;
        p2 = palavras;
        Collections.shuffle(p2);//embaralhando
        palavrasEmbaralhadas = new ArrayList<>();
        for(int i=0;i<tamanho;i++){
            palavrasEmbaralhadas.add(p2.get(i));
        }
    }
    
    private static void getArquivos(){
        ArquivoPalavrasValidadas[] a = EncontraArquivos.PegaArquivosDo(site);
        for(ArquivoPalavrasValidadas s:a){
            getPalavras(s.getId());
        }
    }
    private static void getPalavras(char c){
        PalavrasValidadasDados pvd = new PalavrasValidadasDados(site,c);
        ArrayList<Palavra> plv = pvd.recuperaPalavrasAtivas();
        for(Palavra p:plv){
            if(!palavras.contains(p))
                palavras.add(p);
        }
        Collections.shuffle(palavras, new Random(System.nanoTime()));
    }
    private static void navegaPelasPalavras() throws Exception{
            getArquivos();

            setaPalavrasAleatorias(10);
            
            if (palavrasEmbaralhadas.size()<=0)
                throw new Exception("Sem palavras salvas");
            else
                for(int i=0;i<palavrasEmbaralhadas.size();i++){//varrendo as palavras salvas
                    //modelo.addRow(palavras.get(i));     
                    navegaPalavra(palavrasEmbaralhadas.get(i),i);
                }
            driver.quit();
            
    }

    private static void navegaPalavra(Palavra palavra, int indice) {
        int pagina=1;
        int paginador=0;
        boolean encontrou;
        if(driver==null)
            driver = new FirefoxDriver();
        
        for(pagina=1;pagina<=10;pagina++){
            encontrou = false;
            contador++;
            //após 10 interações com o google (busca/listagem de página), vamos a uma ação
            //para despistar!
            if(contador>20){                
                acaoEvasiva();
                contador=0;
            }    
            driver.get("https://www.google.com.br/search?q="+palavra.getPalavra()+"&start="+paginador);
            List<WebElement> linhas = driver.findElements(By.className("g"));

            int linha=1;
            for(WebElement e:linhas){
                linhasLidas++;
                if (Base.existsElement(e,By.className("_Rm"))){
                    WebElement link = e.findElement(By.className("_Rm"));
                    System.out.println(linhasLidas+": "+(indice+1)+ " de "+(palavrasEmbaralhadas.size()*contaRodadas)+" -Procurando "+site.getMainDomain()+" em"
                            + " "+link.getText());
                    
                    if(link.getText().contains(site.getMainDomain())){
                        link.click();
                        WebElement href=null;
                        if(Base.existsElement(e, By.tagName("a")))
                            href = e.findElement(By.tagName("a"));
                        if(href!=null)
                            href.click();
                        try{
                            acaoEvasiva.aguarda(30000);
                            acaoEvasiva.navegaEm(driver, 1);
                        }catch(Exception es){
                            System.out.println(es.toString());
                        }    
                        encontrou=true;
                        break;
                    }    
                }
                linha++;
            }
            if(encontrou) break;//forçando a saída, caso encontre a palavra em algum link nesta busca
            paginador+=10;
        }    
    }

    private static void acaoEvasiva() {
        try {
            new AcaoEvasivaAleatoria(driver);
        } catch (Exception ex) {
            //Logger.getLogger(PageRankAnalytic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
