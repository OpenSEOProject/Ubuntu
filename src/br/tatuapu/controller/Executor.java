/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.controller;

import br.tatuapu.model.Usuario;
import br.tatuapu.util.ArquivoPalavras;
import br.tatuapu.view.TelaAddNewSite;
import br.tatuapu.view.TelaCarregaDadosSitesAcompanhados;
import br.tatuapu.view.TelaCarregaPalavrasRanqueadas;
import br.tatuapu.view.TelaLogin;
import br.tatuapu.view.TelaPrincipal;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author tatuapu
 */
public class Executor {
    private static JFrame janelaAtiva;
    private static JFrame telaAddNewSite;
    private static JFrame telaCarregaDadosSitesAcompanhados;
    private static JFrame telaPrincipal, telaCarregaPalavrasRanqueadas, telaLogin;
    private static WebDriver driver;
    private static Usuario usuarioLogado = null;
    
    public static void main(String[] args){
        
        if(usuarioLogado!=null){
           abreTelaPrincipal();
        }else{
           abreTelaLogin();
        }   
    } 
    public static void abreTelaPrincipal(){
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        setTelaPrincipal(telaPrincipal);
    }
    public static JFrame getTelaPrincipal(){
        return telaPrincipal;
    }
    public static void setTelaPrincipal(JFrame janela){
        telaPrincipal = janela;
    }
    public static JFrame getTelaAddNewSite(){
        return telaAddNewSite;
    }
    public static void setJanelaAtiva(JFrame janela){
              janelaAtiva = janela;
    }
    public static JFrame getJanelaAtiva(){
        return janelaAtiva;
    }

    public static void setTelaAddNewSite(TelaAddNewSite janela){
        telaAddNewSite = janela;
    }

    public static JFrame getTelaCarregaDadosSitesAcompanhados() {
        return telaCarregaDadosSitesAcompanhados;
    }

    public static void setTelaCarregaDadosSitesAcompanhados(TelaCarregaDadosSitesAcompanhados janela) {
        telaCarregaDadosSitesAcompanhados = janela;
    }

    public static JFrame getTelaCarregaPalavrasRanqueadas() {
        return telaCarregaPalavrasRanqueadas;
    }

    public static void setTelaCarregaPalavrasRanqueadas(TelaCarregaPalavrasRanqueadas janela) {
        telaCarregaPalavrasRanqueadas = janela;
    }

    /**
     * @return the usuarioLogado
     */
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    private static void abreTelaLogin() {
        telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }

    public static void setUsuarioLogado(Usuario u) {
        usuarioLogado = u;
    }
}
