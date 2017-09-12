package br.tatuapu.model;

import br.tatuapu.util.DadosLoader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tatuapu
 */
public class Dados implements Serializable {
    private ArrayList<Usuario> usuarios;
    public Dados(){
        this.usuarios = new ArrayList<Usuario>();
    }
    
    public void getUsuarios(){
        this.usuarios = DadosLoader.recuperaDados().usuarios;
        if (this.usuarios == null)
            this.usuarios = new ArrayList<Usuario>();
    }
    public Usuario login(String n, String p){
        for(Usuario u:usuarios){
            try {
                if(u.getNomeUsuario().equals(n) && u.comparaSenha(p))
                    return u;
            } catch (Exception ex) {
                return null;
            }
        }
        return null;
    }
    public void addUsuario(Usuario u){
        usuarios.add(u);
        salvaDados();
    }

    private void salvaDados() {
        DadosLoader.salvaDados(this);
    }
    public int getQtdUsuarios(){
        if(this.usuarios==null)
            return 0;
        return this.usuarios.size();
    }

}
