package br.tatuapu.util;

import br.tatuapu.controller.Executor;
import br.tatuapu.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author tatuapu
 */
public class CarregadorDadosSearchConsole {
    private static WebDriver driver;
    private ArrayList<String> listaPalavras;
    private final Site site;
    
    public CarregadorDadosSearchConsole(Site site){
        this.site = site;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().contains("windows"))
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        else
            System.setProperty("webdriver.gecko.driver", "geckodriver");
       
        this.listaPalavras = new ArrayList<>();
    }
    /**
     * Captura lista de palavras apresentadas no google search analytics
     * @return ArrayList<String> com a lista das palavras encontradas no google
     * search analytics. É a base inicial do processo de escolha de palavras
     * para otimização
     */
    public ArrayList<String> capturaPalavras(){
        driver = new FirefoxDriver();
        driver.get("https://www.google.com/webmasters/tools/search-analytics?hl=pt-BR&authuser=0&siteUrl="+site.getUrlSearchConsole());
        System.out.println(driver.getTitle());
        WebElement elemento = driver.findElement(By.id("identifierId"));
        //System.out.println(elemento.getAttribute("style"));
        elemento.sendKeys(Executor.getUsuarioLogado().getGoogleEmail());
        elemento.submit();
        driver.findElement(By.className("O0WRkf")).click();
        
        //WebDriverWait wait = new WebDriverWait(driver, 10);  // timeout em segundos
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.className("headingText")));
        try{
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarregadorDadosSearchConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ps = Executor.getUsuarioLogado().getGoogleSenha();
        driver.findElement(By.className("whsOnd")).sendKeys(ps);
        driver.findElement(By.className("O0WRkf")).click();
        //WebElement btn = driver.findElement(By.id("searchsubmit"));
        //btn.click();
        try{
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarregadorDadosSearchConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-name-logo")));
        String title = "search console - search analytics";
       
        if(driver.getTitle().toLowerCase().contains(title)){
            //entramos
            System.out.println("entramos");
            WebDriverWait wait = new WebDriverWait(driver, 15);  // timeout em segundos
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("wmt-jstable-row")));

            List<WebElement> linhas = driver.findElements(By.className("wmt-jstable-row"));
            List<WebElement> linhas2 = driver.findElements(By.className("wmt-jstable-row-odd"));
            linhas.addAll(linhas2);
            System.out.println(linhas.size());
            listaPalavras = new ArrayList<>();
            for(WebElement e: linhas){
                String span = e.findElement(By.cssSelector("span")).getText();
                System.out.println(span);
                listaPalavras.add(span);                
            }
        }
        driver.quit();
        return listaPalavras;
    }
    
}
