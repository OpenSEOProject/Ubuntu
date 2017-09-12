/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tatuapu.model;

import br.tatuapu.util.Encrypter;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tatuapu
 */
public class Usuario implements Serializable {
    private final String nomeUsuario;
    private final String senha;
    private String googleEmail;
    private String googleSenha;
        
    public Usuario(String nm, String pss){
        this.nomeUsuario = nm;        
        this.senha = pss;        
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * gets crypted senha
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }
    public boolean comparaSenha(String s) throws Exception{
        String senhaC;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(s.getBytes()));
            senhaC= hash.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception("Erro ao criptografar senha. Usuário não criado");
        }
        if(senhaC.equals(senha))
            return true;
        else
            return false;
    }

    /**
     * @return the googleEmail
     */
    public String getGoogleEmail() {
        return googleEmail;
    }

    /**
     * @param googleEmail the googleEmail to set
     */
    public void setGoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }

    /**
     * return decrypted senha
     * @return the googleSenha
     */
    public String getGoogleSenha() {
        return Encrypter.decrypt(googleSenha);
    }

    /**
     * set crypted senha
     * @param googleSenha the googleSenha to set
     */
    public void setGoogleSenha(String googleSenha) {
        this.googleSenha = Encrypter.encrypt(googleSenha);
    }
}
