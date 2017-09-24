package br.tatuapu.util;

import java.util.ArrayList;
import br.tatuapu.model.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author tatuapu
 */
public class PageRankAnalytic {

    private static int contador;
    private static WebDriver driver;
    private static ArrayList<Palavra> listaPalavrasValidadas;
    static Site site;
    private static ArrayList<Palavra> palavras;
    private static int linhasLidas;
    
    public static ArrayList<Palavra> getPageRank(Site site) throws Exception{
        PageRankAnalytic.site = site;
        listaPalavrasValidadas = new ArrayList<Palavra>();
        PalavrasDados palavrasDados = new PalavrasDados(site);
            palavras = palavrasDados.recuperaPalavrasAtivas();
            if (palavras.size()<=0)
                throw new Exception("Sem palavras salvas");
            else
                for(int i=0;i<palavras.size();i++){//varrendo as palavras salvas
                    //modelo.addRow(palavras.get(i));     
                    procuraPalavra(palavras.get(i));
                }
        return listaPalavrasValidadas;
    }

    private static void procuraPalavra(Palavra palavra) {
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
            if(contador>4){                
                acaoEvasiva();
                contador=0;
            }    
            driver.get("https://www.google.com.br/search?q="+palavra.getPalavra()+"&start="+paginador);
            List<WebElement> linhas = driver.findElements(By.className("g"));

            int linha=1;
            for(WebElement e:linhas){
                linhasLidas++;
                if (existsElement(e)){
                    WebElement link = e.findElement(By.className("_Rm"));
                    System.out.println(linhasLidas+" de "+(palavras.size()*100)+" -Procurando "+site.getMainDomain()+" em"
                            + " "+link.getText());
                    if(link.getText().contains(site.getMainDomain())){
                        listaPalavrasValidadas.add(new Palavra(palavra.getPalavra(), pagina, linha));
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
    private static boolean existsElement(WebElement e) {
        try {
            e.findElement(By.className("_Rm"));
        }catch (NoSuchElementException error) {
            return false;
        }
        return true;
    }
}
